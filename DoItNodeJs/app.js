// Express 기본 모듈 불러오기
var express = require('express')
    , http = require('http')
    , path = require('path');

var crypto = require('crypto');

// Express의 미들웨어 불러오기
var bodyParser = require('body-parser')
    , cookieParser = require('cookie-parser')
    , static = require('serve-static')
    , errorHandler = require('errorhandler');

// 에러 핸들러 모듈 사용
var expressErrorHandler = require('express-error-handler');

// Session 미들웨어 불러오기
var expressSession = require('express-session');

// 익스프레스 객체 생성
var app = express();

var mongoose = require('mongoose');

var user = require('./routes/user');

var config = require('./config/config');

var route_loader = require('./routes/route_loader');


// 기본 속성 설정
app.set('port', process.env.PORT || 3000);

// body-parser를 이용해 application/x-www-form-urlencoded 파싱
app.use(bodyParser.urlencoded({ extended: false }))

// body-parser를 이용해 application/json 파싱
app.use(bodyParser.json())

// public 폴더를 static으로 오픈
app.use('/public', static(path.join(__dirname, 'public')));

// cookie-parser 설정
app.use(cookieParser());

// 세션 설정
app.use(expressSession({
    secret:'my key',
    resave:true,
    saveUninitialized:true
}));


//===== 데이터베이스 연결 =====//

var UserSchema;

var UserModel;


// 데이터베이스 객체를 위한 변수 선언
var database;

//데이터베이스에 연결
function connectDB() {
    // 데이터베이스 연결 정보
    var databaseUrl = 'mongodb://localhost:27017/local';

    console.log('데이터베이스 연결을 시도합나다.');
    mongoose.Promise = global.Promise;
    mongoose.connect(databaseUrl);
    database = mongoose.connection;

    database.on('error', console.error.bind(console, 'monggose connection error.'));
    database.on('open', function () {
        console.log('데이터베이스에 연결되었습니다. : ' + databaseUrl);

        createUserSchema();
        console.log('UserSchema 정의함.');

        // static(name, fn) 모델 객체에 사용할 수 있는 함수를 등록
        UserSchema.static('findById', function (id, callback) {
            return this.find({id:id}, callback);
        });

        UserSchema.static('findAll', function (callback) {
            return this.find({}, callback);
        });



    });

    database.on('disconnected', function () {
        console.log('연결이 끊어 졌습니다. 5초 후 다시 연결합니다.');
        setInterval(connectDB, 5000);
    });
}


function createUserSchema() {
    UserSchema = require('./database/user_schema').createSchema(mongoose);

    UserModel = mongoose.model("users3", UserSchema);
    console.log('UserModel 정의함');

    user.init(database, UserSchema, UserModel);

}


//===== 라우팅 함수 등록 =====//

// 라우터 객체 참조
route_loader.init(app, express.Router());

// 라우터 객체 등록
// app.use('/', router);


// 404 에러 페이지 처리
var errorHandler = expressErrorHandler({
    static: {
        '404': './public/404.html'
    }
});

app.use( expressErrorHandler.httpError(404) );
app.use( errorHandler );


// Express 서버 시작
http.createServer(app).listen(app.get('port'), function(){
    console.log('서버가 시작되었습니다. 포트 : ' + app.get('port'));

    // 데이터베이스 연결을 위한 함수 호출
    connectDB();

});
