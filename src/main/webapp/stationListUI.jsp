<%--
  Created by IntelliJ IDEA.
  User: 김병석
  Date: 2023-01-19
  Time: 오전 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BSS 스테이션 목록 화면(NHN Toast UI 적용)</title>
    <%--CDN을 통한 NHN Toast 그리드 가져오기--%>
    <link rel="stylesheet" href="https://uicdn.toast.com/grid/latest/tui-grid.css" />
    <script src="https://uicdn.toast.com/grid/latest/tui-grid.js"></script>
    <script> let jsonData = ${stationResult};</script>
    <script>
        window.onload = function(){
            /*$.ajax({
                url : "/preBoard/getPreBoardList",
                method :"GET",
                dataType : "JSON",
                success : function(result){
                    grid.resetData(result);
                }
            });*/
            const grid = new tui.Grid({
                el: document.getElementById('grid'),
                scrollX: false,
                scrollY: false,
                columns: [
                    {
                        header: 'NO',
                        name: 'index',
                    },
                    {
                        header: 'ID',
                        name: 'BssId',
                    },
                    {
                        header: '이름',
                        name: 'BssNm'
                    },
                    {
                        header: '설치 날짜',
                        name: 'BssInstallYmd'
                    },
                    {
                        header: '설치 지역',
                        name: 'BssArea',
                    },
                    {
                        header: '설지 주소',
                        name: 'BssAddress',
                    },
                    {
                        header: '사용 가능 여부',
                        name: 'BssUseYn',
                    },
                    {
                        header: '설치 위도',
                        name: 'BssLati',
                    },
                    {
                        header: '설치 경도',
                        name: 'BssLongi',
                    }
                ]
            });

            jsonData.forEach(row => {
                grid.appendRow(row);
            });

            grid.on('click', (ev) => {
                alert(jsonData[ev.rowKey].BssId)
                window.location.replace("/stationDetail?bssId="+jsonData[ev.rowKey].BssId);
            } )
        };
    </script>
</head>
<body>
<h2>BSS 스테이션 목록</h2>
<div id="grid">
</div>
</body>
</html>
