package com.TestNice.servlet.eventPublisher;

import com.TestNice.servlet.eventClass.DistanceEvent;
import com.TestNice.servlet.eventClass.LoginEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventCaculateDistanceService {

    private final ApplicationEventPublisher applicationEventPublisher;

    public DistanceEvent calculateDistance() {
        DistanceEvent distanceEvent = new DistanceEvent(this);
        applicationEventPublisher.publishEvent(distanceEvent);
        return distanceEvent;

    }

    public DistanceEvent StationDetail(String bssId) {
        DistanceEvent distanceEvent = new DistanceEvent(this, bssId);
        applicationEventPublisher.publishEvent(distanceEvent);
        return distanceEvent;

    }
}
