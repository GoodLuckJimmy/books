var crypto = require('crypto');

var Schema = {};

Schema.createSchema = function(mongoose) {
    var UserSchema = mongoose.Schema({
        id: {type : String, required : true, unique : true, 'default' : ' '},
        hashed_password : {type : String, required : true, 'default' : ' '},
        salt: {type : String, required : true},
        name: {type : String, index : 'hashed', 'default' : ' '},
        age: {type : Number, 'default' : -1 },
        created_at : {type : Date, index : {unique : false}, 'default' : Date.now},
        updated_at : {type : Date, index : {unique : false}, 'default' : Date.now}
    });

    UserSchema
        .virtual('password')
        .set(function (password) {
            this._password = password;
            this.salt = this.makeSalt();
            this.hashed_password = this.encryptPassword(password);
            console.log('virtual password 호출됨 : ' + this.hashed_password);
        })
        .get(function () {
            return this._password;
        });

    UserSchema.method('encryptPassword', function (plainText, inSalt) {
        if (inSalt) {
            return crypto.createHmac('sha1', inSalt).update(plainText).digest('hex');
        } else {
            return crypto.createHmac('sha1', this.salt).update(plainText).digest('hex');
        }
    });

    UserSchema.method('makeSalt', function () {
        return Math.round((new Date().valueOf() * Math.random())) + '';
    });


    UserSchema.method('authenticate', function (plainText, inSalt, hashed_password) {
        if (inSalt) {
            console.log('authenticate 호출됨 : %s -> %s : %s', plainText,
                this.encryptPassword(plainText, inSalt), hashed_password);
            return this.encryptPassword(plainText, inSalt) == hashed_password;
        } else {
            console.log('authenticate 호출됨 : %s -> %s : %s', plainText,
                this.encryptPassword(plainText), this.hashed_password);
            return this.encryptPassword(plainText) == this.hashed_password;
        }
    });

    UserSchema.path('id').validate(function (id) {
        return id.length;
    }, 'id 컬럼이 없습니다.');

    UserSchema.path('name').validate(function (name) {
        return name.length;
    }, 'name 칼럼의 값이 없습니다.');

	console.log('UserSchema 정의함.');

	return UserSchema;
};

// module.exports에 UserSchema 객체 직접 할당
module.exports = Schema;

