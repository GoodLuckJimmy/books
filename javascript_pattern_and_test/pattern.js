function Marsupial(name, nocturnal) {
    if (!(this instanceof Marsupial)) {
        throw new Error("이 객체는 new를 사용하여 생성해야 합니다.");
    }

    this.name = name;
    this.isNocturnal = nocturnal;
}

// 각 객체는 isAwake 사본 하나를 공유한다.
Marsupial.prototype.isAwake = function(isNight) {
    return isNight === this.isNocturnal;
}


/******************* new 사용한 생성자***************/
var isNightTime = true;


var maverick = new Marsupial('매버릭', true);
var slider = new Marsupial('슬라이더', false);

console.log(maverick.isAwake(isNightTime)); // true
console.log(slider.isAwake(isNightTime)); // false

console.log(maverick.isAwake === slider.isAwake); // true



/******************* 고전적 상속 ***************/

function Kangaroo(name) {
    if (!(this instanceof Kangaroo)) {
        throw new Error('이 객체는 new를 사용하여 생성해야 합니다.');
    }
    this.name = name;
    this.isNocturnal = false; // 단점: 이부분이 부모와 중복됨
}


Kangaroo.prototype = new Marsupial();
Kangaroo.prototype.hop = function() {
    return this.name + "가 껑충 뛰었어요!";
};

var jester = new Kangaroo('제스터');

console.log(jester.name);

isNightTime = false;
console.log(jester.isAwake(isNightTime)); //true
console.log(jester.hop());

console.log(jester instanceof Kangaroo); // true
console.log(jester instanceof Marsupial); // true


/******************* 함수형 상속 ***************/
var AnimalKingdom = AnimalKingdom || {};

AnimalKingdom.marsupial = function(name, nocturnal) {
    var instanceName = name;
    var instanceIsNocturnal = nocturnal;

    return {
        getName: function() {
            return instanceName;
        },
        getIsNocturnal: function() {
            return instanceIsNocturnal;
        }
    };
}

AnimalKingdom.kangaroo = function(name) {
    var baseMarsupial = AnimalKingdom.marsupial(name, false);

    baseMarsupial.hop = function() {
        return baseMarsupial.getName() + '가 껑충 뛰었어요!';
    };
    return baseMarsupial;
}

var jester2 = AnimalKingdom.kangaroo('제스터');
console.log(jester2.getName()); //제스터
console.log(jester2.getIsNocturnal()); // false
console.log(jester2.hop()); // 제스터가 껑충 뛰었어요!


/******************* 멍키 패칭***************/
var human = {
    useSignLanuage: function() {
        return '손을 움직여 수화하고 있어. 무슨 말인지 알겠니?';
    }
};

var koko = {};

koko.useSignLanuage = human.useSignLanuage;
console.log(koko.useSignLanuage()); // 손을 움직여 수화하고 있어. 무슨 말인지 알겠니?
// 위 코드는 객체에 없는 함수를 불러 쓸 때 문제가 됨.

var MyApp = MyApp || {};

MyApp.Hand = function() {
    this.dataAboutHand = {};
};
MyApp.Hand.prototype.arrangeAndMovie = function(sign) {
    this.dataAboutHand = '새로운 수화 동작';
}

MyApp.Human = function(handFactory) {
    this.hands = [handFactory(), handFactory()];
};
MyApp.Human.prototype.useSignLanuage = function(message) {
    var sign = {};

    //메시지를 sign에 인코딩한다.
}