/**********************************************************/
describe('createReservation(passenger, flight)', function() {
    var testPassenger = null;
    var testFlight = null;
    var testReservation = null;
    var testSaver = null;

    beforeEach(function() {
        testPassenger = {
            firstName: '윤지',
            lastName: '김'
        }; 

        testFlight = {
            number: '3443',
            carrier:'대한항공',
            destination: '울산'
        };

        testSaver = new ReservationSaver();
        spyOn(testSaver, 'saveReservation');

        testReservation = createReservation(testPassenger, testFlight, testSaver);
    });

    it('주어진 passenger를 passengerInfo 프로퍼티에 할당한다.', function() {
        expect(testReservation.passengerInfo).toBe(testPassenger);
    });

    it('주어진 flight를 flightInfo 프로퍼티에 할당한다.', function() {
        expect(testReservation.flightInfo).toBe(testFlight);
    });

    it('예약 정보를 저장한다.', function() {
        expect(testSaver.saveReservation).toHaveBeenCalled();
    });
});

/**********************************************************/
describe('DiContainer', function() {
    var container;

    beforeEach(function() {
        container = new DiContainer();
    });

    describe('register(name, dependencies, func)', function() {
        it('인자가 하나라도 빠졌거나 타입이 잘못되면 예외를 던진다.', function() {
            var badArgs = [
                [], // 인자가 아예 없는 경우
                ['Name'], // name만 있는경우
                ['Name', ['Dependency1', 'Dependency2']], // name과 dependency만 있는경우
                ['Name', function() {}], // dependencies가 빠진경우
                // 타입이 잘못된 경우들
                [1,['a','b'], function(){}],
                ['Name',[1, 2], function(){}],
                ['Name',['a', 'b'], 'should be a function']
            ];

            badArgs.forEach(function(args) {
                expect(function() {
                    container.register.apply(container, args);
                }).toThrowError(container.message.registerRequiresArgs);
            });
        });
    });

    describe('get(name)', function() {
        it('성명이 등록되어 있지 않으면 undefined를 반환한다.', function() {
            expect(container.get('notDefined')).toBeUndefined();
        });

        it('등록된 함수를 실행한 결과를 반환한다', function() {
            var name = 'MyName';
            var returnFromRegisteredFunction = "something";

            container.register(name, [], function() {
                return returnFromRegisteredFunction;
            });

            expect(container.get(name)).toBe(returnFromRegisteredFunction);
        });

        it('등록된 함수에 의존성을 제공한다.', function() {
            var main = 'main';
            var mainFunc;
            var dep1 = 'dep1';
            var dep2 = 'dep2';

            container.register(dep1, [], function() {
                return function() {
                    return 1;
                };
            });

            container.register(dep2, [], function() {
                return function() {
                    return 2;
                }
            });

            container.register(main, [dep1, dep2], function(dep1Func, dep2Func) {
                return function() {
                    return dep1Func() + dep2Func();
                };
            });

            mainFunc = container.get(main);
            expect(mainFunc()).toBe(3);
        });
    });
});
