var database;
var UserSchema;
var UserModel;

var init = function (db, schema, model) {
    console.log('init 호출');

    database = db;
    UserSchema = schema;
    UserModel = model;
}

var login = function (req, res) {
    console.log('user 모듈 안에 있는 login 호출됨.');

    console.log('/process/login 호출됨.');

    // 요청 파라미터 확인
    var paramId = req.body.id || req.query.id;
    var paramPassword = req.body.password || req.query.password;

    console.log('요청 파라미터 : ' + paramId + ', ' + paramPassword);

    // 데이터베이스 객체가 초기화된 경우, authUser 함수 호출하여 사용자 인증
    if (database) {
        authUser(database, paramId, paramPassword, function(err, docs) {
            if (err) {throw err;}

            // 조회된 레코드가 있으면 성공 응답 전송
            if (docs) {
                console.dir(docs);

                // 조회 결과에서 사용자 이름 확인
                var username = docs[0].name;

                res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
                res.write('<h1>로그인 성공</h1>');
                res.write('<div><p>사용자 아이디 : ' + paramId + '</p></div>');
                res.write('<div><p>사용자 이름 : ' + username + '</p></div>');
                res.write("<br><br><a href='/public/login.html'>다시 로그인하기</a>");
                res.end();

            } else {  // 조회된 레코드가 없는 경우 실패 응답 전송
                res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
                res.write('<h1>로그인  실패</h1>');
                res.write('<div><p>아이디와 패스워드를 다시 확인하십시오.</p></div>');
                res.write("<br><br><a href='/public/login.html'>다시 로그인하기</a>");
                res.end();
            }
        });
    } else {  // 데이터베이스 객체가 초기화되지 않은 경우 실패 응답 전송
        res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
        res.write('<h2>데이터베이스 연결 실패</h2>');
        res.write('<div><p>데이터베이스에 연결하지 못했습니다.</p></div>');
        res.end();
    }
};

var authUser = function(database, id, password, callback) {
    console.log('authUser 호출됨 : ' + id + ', ' + password);

    UserModel.findById(id, function (err, results) {
        if (err) {
            callback(err, null);
            return;
        }

        console.log('아이디[%s]로 사용자 검색 결과', id );
        console.dir(results);

        if (results.length > 0) {
            console.log('아이디와 일치하는 사용자 찾음.');

            var user = new UserModel({id:id});
            var authenticated = user.authenticate(password, results[0]._doc.salt,
                results[0]._doc.hashed_password);

            if (authenticated) {
                console.log('비밀번호 일치함');
                callback(null, results);
            } else {
                console.log('비밀번호 일치하지 않음');
                callback(null, null);
            }

        } else {
            console.log('아이디와 일치하는 사용자를 찾지 못함.');
            callback(null, null);

        }
    });
};

var adduser = function (req, res) {
    console.log('/process/addUser 호출됨');

    var paramId = req.body.id || req.query.id;
    var paramPassword = req.body.password || req.query.password;
    var paramName = req.body.name || req.query.name;

    console.log('요청 파라미터 : ' + paramId + ', ' + paramPassword + ', ' + paramName);

    if (database) {
        addUser(database, paramId, paramPassword, paramName, function (err, result) {
            if (err) {
                throw err;
            }

            if (result && result.insertedCount > 0) {
                console.dir(result);

                res.writeHead('200', {'Content-Type': 'text/html;charset=utf8'});
                res.write('<h2>사용자 추가 성공</h2>');
                res.end();
            } else {
                res.writeHead('200', {'Content-Type': 'text/html;charset=utf8'});
                res.write('<h2>사용자 추가 실패</h2>');
                res.end();
            }
        });
    } else {
        res.writeHead('200', {'Content-Type' : 'text/html;charset=utf8'});
        res.write('<h2>데이터베이스 연결 실패</h2>');
        res.end();
    }
};

var addUser = function (database, id, password, name, callback) {
    console.log('addUser 호출됨 : ' + id + ', ' + password + ', ' + name);

    var user = new UserModel({"id": id, "password": password, "name": name});

    user.save(function (err) {
        if (err) {
            callback(err, null);
            return;
        }
        console.log('사용자 데이터 추가함');
        callback(null, user);
    });
};

var listuser = function (req, res) {
    console.log('/process/listuser 호출됨');

    if (database) {
        UserModel.findAll(function (err, results) {
            if (err) {
                console.error('사용자 리스트 조회 중 오류 발생 : ' + err.stack);

                res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
                res.write('<h2>사용자 리스트 조회 중 오류 발생</h2>');
                res.write('<p>' + err.stack + "</p>");
                res.end();

                return;
            }

            if (results) {
                console.dir(results);

                res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
                res.write('<h2>사용자 리스트</h2>');
                res.write('<div><ul>');

                for (var i = 0; i < results.length; i++) {
                    var curld = results[i]._doc.id;
                    var curName = results[i]._doc.name;
                    res.write('<li>#' + i + ' : ' + curld + ', ' + curName + '</li>');
                }

                res.write('</ul></div>');
                res.end();
            } else {
                res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
                res.write('<h2>사용자 리스트 조회실패</h2>');
                res.end();

            }
        });
    } else {
        res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
        res.write('<h2>DB 연결 실패</h2>');
        res.end();

    }
};

module.exports.init = init;
module.exports.login = login;
module.exports.adduser = adduser;
module.exports.listuser = listuser;

