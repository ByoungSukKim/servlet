<%--
  Created by IntelliJ IDEA.
  User: 김병석
  Date: 2023-01-20
  Time: 오전 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BSS 스테이션 항목 상세</title>
    <link rel="stylesheet" href="https://uicdn.toast.com/grid/latest/tui-grid.css" />
    <script src="https://uicdn.toast.com/grid/latest/tui-grid.js"></script>
    <script type="text/javascript"
            src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=vm857ifcks"></script>
    <script>

        let jsonData = ${detailResult};
        let staionIn = jsonData[0].title_id;
        let statinLati = jsonData[4].BSSLati;
        let statinLogi = jsonData[4].BSSLongi;

        /*이미지 호출을 위한 임시 값*/
        let status = "Ne";
        let statusOK = "OK";
        let statusNe = "Ne";
        let statusRe = "Re";
        let statusWar = "War";

    </script>


    <script>

        class CustomRenderer {

            constructor(props) {
                const el = document.createElement('img');

                el.src = 'some-image-link';

                this.el = el;
                this.render(props);
            }

            getElement() {
                return this.el;
            }

            render(props) {
                // you can change the image link as changes the `value`
                /*this.el.src = String(props.value);*/
                if (status == statusOK) {

                    this.el.src = "../css/img/BSSStatusOK.png";

                } else if (status == statusNe) {

                    this.el.src = "../css/img/BSSNotExchange.png";

                } else if (status == statusRe) {

                    this.el.src = "../css/img/BSSRepair.png";

                } else if (status == statusWar) {

                    this.el.src = "../css/img/BSSWarning.png";

                }

            }
        }



        window.onload = function() {

            const grid = new tui.Grid({

                el: document.getElementById('grid'),
                scrollX: false,
                scrollY: false,
                columns: [
                    {
                        header: jsonData[0].title_id,
                        name: 'title'
                    },

                    {
                        header: jsonData[4].BssId,
                        name: 'content'
                    }
                ],

                data: [
                    {
                        title: jsonData[0].title_Nm ,
                        content: jsonData[4].BssNm

                    },

                    {
                        title: jsonData[0].title_area ,
                        content: jsonData[4].BssArea

                    }
                    ,
                    {
                        title: jsonData[0].title_address ,
                        content: jsonData[4].BssAddress

                    }
                    ,
                    {
                        title: jsonData[0].title_install ,
                        content: jsonData[4].InstallDate

                    }
                ]
            });

            const gridBattery = new tui.Grid({

                el: document.getElementById('gridBattery'),
                scrollX: false,
                scrollY: false,
                columns: [
                    {
                        header: jsonData[1].title_status,
                        name: 'status',

                        // you can use like below in `v4.9.0`
                        renderer: CustomRenderer,

                        // or regardless of version
                        renderer: {

                            type: CustomRenderer,

                        }
                    },

                    {
                        header: jsonData[1].title_exchage,
                        name: 'exchage'
                    },

                    {
                        header: jsonData[2].title_full,
                        name: 'title_full'
                    },

                    {
                        header: jsonData[2].title_charge,
                        name: 'title_charge'
                    },

                    {
                        header: jsonData[2].title_reservation,
                        name: 'title_reservation'
                    },

                    {
                        header: jsonData[2].title_error,
                        name: 'title_error'
                    },

                ],
                header: {

                        height: 'auto',

                    complexColumns: [
                        {
                            header: jsonData[1].title_battery,
                            name: 'title_battery',
                            childNames: ['title_full', 'title_charge', 'title_reservation', 'title_error']
                        },

                    ]
                },

                data: [

                    {

                        exchage : 'Ok',
                        title_full: 5,
                        title_charge: 1,
                        title_reservation: 0,
                        title_error: 0,

                    }

                ]
            });

        }
    </script>

</head>
<body>
<h2>BSS 스테이션 상세 요약</h2>
<div id="grid">
</div>
</br>
</br>
<div id="map" style="width:300px; height:300px;">
    <button type="button" onclick="window.open('https://map.naver.com/v5/?c=15.84,0,0,0,dh');">상세 보기</button>
</div>
<script>

    var map = new naver.maps.Map('map', {
        center: new naver.maps.LatLng(statinLati, statinLogi), // 잠실 롯데월드를 중심으로 하는 지도
        zoom: 15
    });

    var marker = new naver.maps.Marker({
        position: new naver.maps.LatLng(statinLati, statinLogi),
        map: map
    });

    naver.maps.Event.addListener(marker, 'click', function(e) {
        var overlay = e.overlay, // marker
            position = overlay.getPosition(),
            url = 'http://map.naver.com/index.nhn?enc=utf8&level=2&lng='+ position.lng() +'&lat='+ position.lat() +'&pinTitle='+jsonData[4].BssNm+'&pinType=SITE';

        window.open(url);
    });

</script>
</br>
</br>
<div id="gridBattery">
</div>




</body>
</html>
