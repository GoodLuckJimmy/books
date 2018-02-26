// Express 기본 모듈
var express = require('express');
var http = require('http');
var path = require('path');

// Express 미들웨어
var bodyParser = require('body-parser');
var cookieParser = require('cookie-parser');
var static = require('serve-static');
var errorHandler = require('errorhandler');

// 몽고디비 모듈
var MongoClient = require('mongodb').MongoClient;
var database;

// 오류 핸들러 모듈 사용
var expressErrorHandler = require('express-error-handler');

// Session 미들웨어 불러오기
var expressSession = require('express-session');

// 익스프레스 객체 생성
var app = express();

// 기본 속성 설정
app.set('port', process.env.PORT || 3000);

// body-parser를 사용해 application/x-www-form-urlencoded 파싱
app.use(bodyParser.urlencoded({ extended: false}));

// body-parser를 사용해 application/json 파싱
app.use(bodyParser.json());

// public 폴더를 static으로 오픈
app.use('/public', static(path.join(__dirname, 'public')));

// cookie-parser 설정
app.use(cookieParser());

// session 설정
app.use(expressSession({
    secret:'my key',
    resave:true,
    saveUninitialized:true
}));


// 라우터 객체 참조
var router = express.Router();

// 로그인 라우팅 함수
router.route('/process/login').post(function (req, res) {
    console.log('/process/login 호출됨');

})

// 라우팅 객체 등록
app.use('/', router);

// 404 오류 처리
var errorHandler = expressErrorHandler({
    static: {
        '404' : './public/404.html'
    }
});

app.use(expressErrorHandler.httpError(404));
app.use(errorHandler);

// 데이터베이스 연결
function connectDB() {
    // 데이터베이스 연결 정보
    var databaseUrl = 'mongodb://localhost:27017/local';

    // 데이터베이스 연결
    MongoClient.connect(databaseUrl, function (err, db) {
       if (err) throw err;
       console.log('데이터베이스에 연결 되었습니다. : ' + databaseUrl);

       // database 변수에 할당
       database = db;
    });

}

var authUser = function (database, id, password, callback) {
    console.log('authUser 호출됨.');

    var users = database.collection('users');

    users.find({"id" : id, "password" : password}).toArray(function (err, docs) {
        if (err) {
            callback(err, null);
            return;
        }

        if (docs.length > 0)_{
            console.log('일치하는 사용자 찾음');
            callback(null, docs);
        } else {
            console.log('일치하는 사용자 못찾음');
            callback(null, null);
        }
    });

}
/*
var passport = require('passport');
var flash = require('connect-flash');
var socketio = require('socket.io');
var cors = require('cors');

app.use(passport.initialize()); // 패스포트 초기화
app.use(passport.session()); // 로그인 세션 유지
app.use(flash());
app.use(cors());
*/

// 서버 시작
http.createServer(app).listen(app.get('port'), function () {
    console.log('서버가 시작되었습니다. 포트: ' + app.get('port'));

    connectDB();
});