// Express 기본 모듈 불러오기
var express = require('express');
var http = require('http');
var path = require('path');

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

var config = require('./config/config');

var route_loader = require('./routes/route_loader');

var database = require('./database/database');

var socketio = require('socket.io');

var cors = require('cors');

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

app.use(cors());

// 라우터 객체 참조
route_loader.init(app, express.Router());

// 404 에러 페이지 처리
var errorHandler = expressErrorHandler({
    static: {
        '404': './public/404.html'
    }
});

app.use( expressErrorHandler.httpError(404) );
app.use( errorHandler );


// Express 서버 시작
var server = http.createServer(app).listen(app.get('port'), function(){
    console.log('서버가 시작되었습니다. 포트 : ' + app.get('port'));

    // 데이터베이스 연결을 위한 함수 호출
    database.init(app, config);

});

var io = socketio.listen(server);
console.log('socket.io 요청을 받을 준비가 되었습니다.');

var login_ids = {};

io.sockets.on('connection', function (socket) {
    console.log('connection info: ', socket.request.connection._peername);
    socket.remoteAddress = socket.request.connection._peername.address;
    socket.remotePort = socket.request.connection._peername.port;

    socket.on('message', function (message) {
        console.log('message 이벤트를 받았습니다.');
        console.dir(message);

        if (message.recepient == 'ALL') {
            console.dir('나를 포함한 모든 사람에게 메세지 전송');
            socket.emit('message', message);
        } else {
            if (login_ids[message.recepient]) {
                io.sockets.connected[login_ids[message.recepient]].emit('message', message);
                sendResponse(socket, 'message', '200', '메시지를 전송했습니다.');
            } else {
                sendResponse(socket, 'login', '404', '상대방의 로그인 ID를 찾을 수 없습니다.');
            }
        }
    });

    socket.on('login', function (login) {
        console.log('login 이벤트');
        console.dir(login);

        console.log('접속한 소켓 ID: ' + socket.id);
        login_ids[login.id] = socket.id;
        socket.login_id = login.id;

        console.log('접속한 클라이언트 ID 개수 : %d', Object.keys(login_ids).length);

        sendResponse(socket, 'login', '200', '로그인 되었습니다.');
    });

    socket.on('room', function (room) {
        console.log('room 이벤트를 받았습니다.');

        if (room.command == 'create') {
            if (io.sockets.adapter.rooms[room.roomId]) {
                console.log('방이 이미 만들어져 있습니다.');
            } else {
                console.log('방을 새로 만듭니다.');

                socket.join(room.roomId);

                var curRoom = io.sockets.adapter.rooms[room.roomId];
                curRoom.id = room.roomId;
                curRoom.name = room.roomName;
                curRoom.owner = room.roomOwner;

            }
        } else if (room.command == 'update') {
            var curRoom = io.sockets.adapter.rooms[room.roomId];
            curRoom.id = room.roomId;
            curRoom.name = room.roomName;
            curRoom.owner = room.roomOwner;
        } else if (room.command == 'delete') {
            socket.leave(room.roomId);

            if (io.sockets.adapter.rooms[room.roomId]) {
                delete io.sockets.adapter.rooms[room.roomId];
            } else {
                console.log('방이 만들어져 있지 않습니다.');
            }
        }

        var roomList = getRoomList();

        var output = {command:'list', rooms:roomList};
        io.sockets.emit('room', output);
    });
});

function sendResponse(socket, command, code, message) {
    var statusObj = {command:command, code:code, message:message};
    socket.emit('response', statusObj);
}

function getRoomList() {
    var roomList = [];

    Object.keys(io.sockets.adapter.rooms).forEach(function (roomId) {
        console.log('current room id: ' + roomId);
        var outRoom = io.sockets.adapter.rooms[roomId];

        var foundDefault = false;
        var index = 0;
        Object.keys(outRoom.sockets).forEach(function (key) {
            if (roomId == key) {
                foundDefault = true;
                console.log('this is a default room');
            }
            index++;
        });

        if(!foundDefault) {
            roomList.push(outRoom);
        }
    });

    console.log('[ROOM LIST]');

    return roomList;
}

