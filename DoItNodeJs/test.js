var mongodb = require('mongodb');
var mongoose = require('mongoose');

var database;
var UserSchema;
var UserModel;



function connectDB() {
    var databaseUrl = 'mongodb://localhost:27017/local';

    mongoose.connect(databaseUrl);
    database = mongoose.connection;

    database.on('error', console.error.bind(console, 'monggose connection error.'));
    database.on('open', function () {
        console.log('데이터베이스에 연결되었습니다. : ' + databaseUrl);

        createUserSchema();

        doTest();
    });
    database.on('disconnected', connectDB);
}

function createUserSchema() {
    UserSchema = mongoose.Schema({
        id : {type: String, required: true, unique: true},
        name : {type: String, index : 'hashed', 'default' :''},
        age : {type: Number, 'default' : -1},
        created_at: {type: Date, index: {unique:false}, 'default' : Date.now},
        updated_at: {type: Date, index: {unique:false}, 'default' : Date.now},

    });

    UserSchema
        .virtual('info')
        .set(function (info) {
            console.log(info);
            var splitted = info.split(' ');
            this.id = splitted[0];
            this.name = splitted[1];
            console.log('vartual info 설정');
        })
        .get(function () {
            return this.id + ' ' + this.name;
        });

    console.log('UserSchema 정의함');

    UserModel = mongoose.model("user4", UserSchema);
    console.log('UserModel 정의함');
}

function doTest() {
    var user = new UserModel({"info" : 'test01 소녀시대'});

    user.save(function (err) {
        if(err) {throw err;}

        console.log("사용자 데이터 추가함");

        findAll();
    });

    console.log('info 속성에 값 할당함.');
}

function findAll() {
    UserModel.find({}, function (err, result) {
        if (err) {throw err;}
        if (result) {
            console.log('조회된 user 문서 객체 #0 -> id : %s, name : %s',
                result[0]._doc.id, result[0]._doc.name);
        }
    });
}

connectDB();