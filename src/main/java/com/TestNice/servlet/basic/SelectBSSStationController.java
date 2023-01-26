package com.TestNice.servlet.basic;

import com.TestNice.servlet.calculate.LocationDistance;
import com.TestNice.servlet.entity.BssStation;
import com.TestNice.servlet.eventClass.DistanceEvent;
import com.TestNice.servlet.eventPublisher.EventCaculateDistanceService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class SelectBSSStationController {

    private EventCaculateDistanceService eventCaculateDistanceService;


    @ApiOperation(value = "리스트 거리 구하기", notes ="1km 이내에 있는 지점 거리 구하기")
    @GetMapping("/getDistance.do")
    public ModelAndView getStationDistance() {
        System.out.println("SelectBSSStation Controller" );
        DistanceEvent distanceEvent = new DistanceEvent(this);
        DistanceEvent resultEvent = eventCaculateDistanceService.calculateDistance();
        List<Optional<BssStation>> stationList = resultEvent.getStationList();
        List<BssStation> resultList = new ArrayList<>();
        //현재 이용자의 좌표 고정값으로 셋팅 (여의나루 지하철 기준)
        double useLati = 37.526939;
        double userLongi = 126.932230;
        for (int i = 0; i < stationList.size()-1; i++) {

            Optional<BssStation> bssStation = stationList.get(i);

            Double stationLati = Double.parseDouble(bssStation.get().getBssLati());
            Double stationLongi = Double.parseDouble(bssStation.get().getBssLongi());

            BssStation bss = new BssStation();
            LocationDistance locationDistance = new LocationDistance();
            double distanceKilometer = locationDistance.distance(useLati, userLongi, stationLati, stationLongi, "kilometer");

            System.out.println("bssName = " + bssStation.get().getBssNm() + "distance(killometer) = " + distanceKilometer);

            if (distanceKilometer < 2 && bssStation.get().getBssUseYn().equals("Y") ) {

                bss.setBssId(bssStation.get().getBssId());
                bss.setBssNm(bssStation.get().getBssNm());
                bss.setBssInstallYmd(bssStation.get().getBssInstallYmd());
                bss.setBssArea(bssStation.get().getBssArea());
                bss.setBssAddress(bssStation.get().getBssAddress());
                bss.setBssUseYn(bssStation.get().getBssUseYn());
                bss.setBssLati(bssStation.get().getBssLati());
                bss.setBssLongi(bssStation.get().getBssLongi());
                System.out.println("bss = " + bss);
            }
            resultList.add(bss);
        }

        ModelAndView mav = new ModelAndView("jsonView");
        int index = 1;
        JsonArray jsonArray = new JsonArray();
        for (BssStation RList: resultList){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("index", index);
            jsonObject.addProperty("BssId", RList.getBssId());
            jsonObject.addProperty("BssNm", RList.getBssNm());
            jsonObject.addProperty("BssInstallYmd", RList.getBssInstallYmd());
            jsonObject.addProperty("BssArea", RList.getBssArea());
            jsonObject.addProperty("BssAddress", RList.getBssAddress());
            jsonObject.addProperty("BssUseYn", RList.getBssUseYn());
            jsonObject.addProperty("BssLati", RList.getBssLati());
            jsonObject.addProperty("BssLongi", RList.getBssLongi());
            jsonArray.add(jsonObject);
            index++;
        }
        mav.setViewName("/stationListUI.jsp");
        mav.addObject("stationResult", jsonArray);
        System.out.println("jsonArray = " + jsonArray);
        return mav;
    }
    @ApiOperation(value = "BSS_ID로 상세 정보 가져오기", notes ="BSS_ID로 상세 정보 가져오기")
    @GetMapping("/stationDetail")
    public ModelAndView stationDetail(@RequestParam String bssId) {
        System.out.println("bssId = " + bssId);
        DistanceEvent distanceEvent = new DistanceEvent(this);
        DistanceEvent eventResult= eventCaculateDistanceService.StationDetail(bssId);
        Optional<BssStation> bssStation = eventResult.getStationDetail();
        BssStation bssResult = bssStation.get();


        JsonArray jsonArray = new JsonArray();

        //BSS 상세
        JsonObject titleObject1 = new JsonObject();
        titleObject1.addProperty("title_id","BSS ID");
        titleObject1.addProperty("title_Nm","BSS 명");
        titleObject1.addProperty("title_area","BSS 지역");
        titleObject1.addProperty("title_address","BSS 주소");
        titleObject1.addProperty("title_install","설치일");

        //BSS 정보
        JsonObject titleObject2 = new JsonObject();
        titleObject2.addProperty("title_status", "BSS 상태");
        titleObject2.addProperty("title_exchage", "교환 가능 여부");
        titleObject2.addProperty("title_battery", "배터리 현황");

        //BSS 정보 배터리 현황
        JsonObject BssBatteryTitle = new JsonObject();
        BssBatteryTitle.addProperty("title_full","충전완료");
        BssBatteryTitle.addProperty("title_charge","충전중");
        BssBatteryTitle.addProperty("title_reservation","예약중");
        BssBatteryTitle.addProperty("title_error","오류");

        //Battery 정보
        JsonObject batteryTitle = new JsonObject();
        batteryTitle.addProperty("slot_title", "slot ID");
        batteryTitle.addProperty("battery_title", "배터리 ID");
        batteryTitle.addProperty("Pair_title", "Pair ID");
        batteryTitle.addProperty("status_title", "Battery 상태");
        batteryTitle.addProperty("soc_title", "SOC");
        batteryTitle.addProperty("degree_title", "온도");

        //각 title array에 넣기
        jsonArray.add(titleObject1);
        jsonArray.add(titleObject2);
        jsonArray.add(BssBatteryTitle);
        jsonArray.add(batteryTitle);

        //json 내용
        JsonObject contentJsonObject = new JsonObject();

        contentJsonObject.addProperty("BssId", bssResult.getBssId());
        contentJsonObject.addProperty("BssNm", bssResult.getBssNm());
        contentJsonObject.addProperty("BssArea", bssResult.getBssArea());
        contentJsonObject.addProperty("BssAddress", bssResult.getBssAddress());
        contentJsonObject.addProperty("InstallDate",bssResult.getBssInstallYmd());
        contentJsonObject.addProperty("BSSLati", bssResult.getBssLati());
        contentJsonObject.addProperty("BSSLongi", bssResult.getBssLongi());

        System.out.println("contentJsonObject = " + contentJsonObject);
        jsonArray.add(contentJsonObject);
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("detailResult", jsonArray);
        mav.setViewName("/stationDetail.jsp");
        return mav;
    }
}
