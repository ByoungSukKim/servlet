package com.TestNice.servlet.eventClass;

import com.TestNice.servlet.entity.BssStation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class DistanceEvent extends ApplicationEvent {

    private String bssId;
    private String bssNm;
    private String bssYmd;
    private String bssArea;
    private String bssLati;
    private String bssLongi;
    private String distance;
    private List<Optional<BssStation>> stationList;
    private Optional<BssStation> StationDetail;

    public DistanceEvent(Object source) {

        super(source);

    }

    public DistanceEvent(Object source, String bssId) {

        super(source);
        this.bssId = bssId;

    }

    public DistanceEvent(Object source, String bssId, String bssNm, String bssYmd, String bssArea, String bssLati, String bssLongi, String distance, List<Optional<BssStation>> stationList) {

        super(source);
        this.bssId = bssId;
        this.bssNm = bssNm;
        this.bssYmd = bssYmd;
        this.bssArea = bssArea;
        this.bssLati = bssLati;
        this.bssLongi = bssLongi;
        this.distance = distance;
        this.stationList = stationList;

    }

}
