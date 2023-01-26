<%--
  Created by IntelliJ IDEA.
  User: 김병석
  Date: 2023-01-09
  Time: 오전 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <meta charset="utf-8">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            max-width: 560px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="py-5 text-center">
        <h2>계정 등록 폼</h2>
    </div>
    <h4 class="mb-3">유저 정보 입력</h4>
    <form action="/login/signIn" method="post">
        <div><label for ="id">ID :</label>
            <input type="text" id="id" name="id" placeholder="id를 입력하세요">
        </div>
        <div><label for ="password">password :</label>
            <input type="text" id="password" name="password"  placeholder="패스워드를 입력하세요">
        </div>
        <div><label for ="userName">userName :</label>
            <input type="text" id="userName" name="userName" placeholder="이름 입력하세요">
        </div>
        <div><label for ="kakao">kakao email :</label>
            <input type="text" id="kakao" name="kakao"  placeholder="카카오 계정을 입력하세요">
        </div>
        <div><label for ="naver">naver email :</label>
            <input type="text" id="naver" name="naver"  placeholder="네이버 계정을 입력하세요">
        </div>
        <div><label for ="google">google email :</label>
            <input type="text" id="google" name="google" placeholder="구글 계정을 입력하세요">
        </div>
        <div><label for ="facebook">facebook email :</label>
            <input type="text" id="facebook" name="facebook" placeholder="페이스북 계정을 입력하세요">
        </div>
        <div><label for ="phone">phone Number :</label>
            <input type="text" id="phone" name="phone" placeholder="전화 번호를 입력하세요">
        </div>
        <hr class="my-4">
        <div class="row">
            <div class="col">
                <button id="insertINF" class="w-100 btn btn-primary btn-lg" type="button">회원 가입</button>
            </div>
            <div class="col">
                <button class="w-100 btn btn-secondary btn-lg"
                        onclick="location.href='items.html'" type="button">취소</button>
            </div>
        </div>
    </form>
    <script>
        document.getElementById("insertINF").addEventListener('click',insert);

        function insert(){

            let data = {

                id: $("#id").val(),
                password: $("#password").val(),
                userName: $("#userName").val(),
                kakao: $("#kakao").val(),
                naver: $("#naver").val(),
                google: $("#google").val(),
                facebook: $("#facebook").val(),
                phone: $("#phone").val()

            };

            $.ajax({
                // 회원가입 수행 요청
                type: "POST",
                url: "/signIn",
                data: JSON.stringify(data), // http body 데이터
                contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지 (MIME)
                dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 String(문자열), 만약 생긴게 json이라면 javascript 오브젝트로 변경
            }).done(function (resp) {
                // 결과가 정상
                alert("회원가입이 완료되었습니다.");
                //console.log(resp);
                location.href = "/login.html";
            }).fail(function (error) {
                // 실패하면 fail 실행
                alert("회원가입이 실패하였습니다.");
                alert(JSON.stringify(error));
            });


        }

    </script>

</div> <!-- /container -->
</body>
</html>
