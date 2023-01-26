<%--
  Created by IntelliJ IDEA.
  User: 김병석
  Date: 2023-01-16
  Time: 오후 2:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


</head>
<body>
<script>
    debugger;
    /*var json = JSON.stringify(${stationResult});*/
    let jsonData = ${stationResult};
</script>
<div class="container" style="max-width: 900px">
    <div class="py-5 text-center">
        <h2>BSS 스테이션 목록</h2>
    </div>
    <div class="row">
        <div class="col">
            <button class="btn btn-primary float-end"
                    onclick="location.href='addForm.html'" type="button">스테이션
                등록</button>
        </div>
    </div>
    <hr class="my-4">
    <div>
        <table class="table">
            <thead>
            <tr>
                <th>NO.</th>
                <th>스테이션 ID</th>
                <th>스테이션 이름</th>
                <th>스테이션 설치 날짜</th>
                <th>스테이션 설치 지역</th>
                <th>스테이션 설지 주소</th>
                <th>스테이션 유저 사용 가능 여부</th>
                <th>스테이션 설치 위도</th>
                <th>스테이션 설치 경도</th>
                <th>상세 보기 버튼</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
        <script>
            var valueList = new Array();
            let index = 1;

            for (var i in jsonData) {

                let tr = document.createElement('tr');
                tr.append(index)

                for(var j in jsonData[i]) {

                    let td = document.createElement('td');
                    td.innerHTML = jsonData[i][j];
                    valueList.push(jsonData[i][j]);
                    tr.append(td);
                }
                index ++;
                let btn = document.createElement('button');
                let btnText = document.createTextNode("More")
                btn.appendChild(btnText);
                btn.className = "btn btn-primary"
                btn.onclick = function() {
                    detail(statianID);
                }
                tr.append(btn);
                document.querySelector("tbody").appendChild(tr);

            }

            function detail(id){

            }
        </script>
        <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
    </div>
</div> <!-- /container -->
</body>
</html>

