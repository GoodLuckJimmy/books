var express = require('express');
var http = require('http');
var path = require('path');

var bodyParser = require('body-parser');
var static =  require('serve-static');

var app = express();

app.set('port', process.env.PORT || 3000);

// body-parser를 사용해 application/x-www-form-urlencoded 파싱
app.use(bodyParser.urlencoded({ extended: false}));

// body-parser를 사용해 application/json 파싱
app.use(bodyParser.json());

app.use(static(path.join(__dirname, 'public')));

app.use(function (req, res, next) {
    console.log('first middle ware');

    var paramId = req.body.id || req.query.id;
    var paramPassword = req.body.password || req.query.password;

    res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
    res.write('<h1>Express 서버에서 응답</h1>');
    res.write('<div><p>Param id : ' + paramId + '</p></div>');
    res.write('<div><p>Param password : ' + paramPassword + '</p></div>');
    res.end();
})

app.use(function (req, res, next) {
   console.log('first middle ware');

   req.user = 'mike';

   next();
});

app.use('/', function (req, res, next) {
    console.log('secend middle ware');

    res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
    res.end('<h1>Express server response from ' + req.user + ' </h1>');

});

http.createServer(app).listen(3000, function () {
   console.log('Express started');

});