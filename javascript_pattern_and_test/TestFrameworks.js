function createReservation(passenger, flight, saver) {
    var reservation = {
        passengerInfo: passenger,
        flightInfo: flight
    };

    saver.saveReservation(reservation);
    return reservation;
}

function ReservationSaver() {
    this.saveReservation = function(reservation) {
        // 원격 작업...
    };
}

/**********************************************************/


Attendee = function(service, messager, attendeeId) {
    if (!(this instanceof Attendee)) {
        return new Attendee(attendeeId);
    }

    this.attendeeId = attendeeId;
    this.service = service;
    this.message = messager;

    // 주어진 세션에 좌석 예약을 시도한다.
    // 성공/실패 여부를 메시지로 알려준다.
    Attendee.prototype.reserve = function(sessionId) {
        if (this.service.reserve(this.attendeeId, sessionId)) {
            this.message.success('좌석 예약이 완료되었습니다.' +
                '고객님은 ' + this.service.getRemainingReservations() +
                ' 좌석을 추가 예약하실 수 있습니다.');
        } else {
            this.message.failure('죄송합니다. 해당 좌석은 예약하실 수 없습니다.');
        }
    };
};


/**********************************************************/

DiContainer = function() {
    if (!(this instanceof DiContainer)) {
        return new DiContainer();
    }
    this.registrations = [];
};

DiContainer.prototype.message = {
    registerRequiresArgs: '이 생성자 함수는 인자가 3개 있어야 합니다: ' +
        '문자열, 문자열 배열, 함수.'
};

DiContainer.prototype.register = function(name, dependencies, func) {
    var ix;

    if (typeof name !=='string'
        || !Array.isArray(dependencies)
        || typeof func !== 'function') {
        throw new Error(this.message.registerRequiresArgs);
    }

    for (ix=0; ix < dependencies.length; ++ix) {
        if (typeof dependencies[ix] !== 'string') {
            throw new Error(this.message.registerRequiresArgs);
        }
    }

    this.registrations[name] = {
        dependencies: dependencies,
        func: func
    };
};

DiContainer.prototype.get = function(name) {
    var self = this;
    var registration = this.registrations[name];
    var dependencies = [];

    if (registration === undefined) {
        return undefined;
    }

    registration.dependencies.forEach(function(dependencyName) {
        var dependency = self.get(dependencyName);
        dependencies.push(dependency === undefined ? undefined : dependency);
    });
    return registration.func.apply(undefined, dependencies);
};