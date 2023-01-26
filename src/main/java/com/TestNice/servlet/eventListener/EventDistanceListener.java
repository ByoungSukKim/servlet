package com.TestNice.servlet.eventListener;

import com.TestNice.servlet.entity.BssStation;
import com.TestNice.servlet.eventClass.DistanceEvent;
import com.TestNice.servlet.repository.BssStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EventDistanceListener {

    private final BssStationRepository bssStationRepository;

    @Autowired
    public EventDistanceListener(BssStationRepository bssStationRepository) {
        this.bssStationRepository = bssStationRepository;
    }


    @Async
    @EventListener
    public void onApplicationEvent(DistanceEvent event) {

        System.out.println("------ onApplicationEvent = 1 Start ------" );

        List<Optional<BssStation>> ListBssStation = bssStationRepository.ListBSS();
        event.setStationList(ListBssStation);

    }

    @Async
    @EventListener
    public void stationDetail(DistanceEvent event) {

        System.out.println("------ onApplicationEvent = DistanceListner Start ------" );
        String bss_id = event.getBssId();
        Optional<BssStation> bssStation = Optional.ofNullable(bssStationRepository.DetailBSS(bss_id));
        event.setStationDetail(bssStation);

    }



}
