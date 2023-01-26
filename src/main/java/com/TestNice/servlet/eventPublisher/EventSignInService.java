package com.TestNice.servlet.eventPublisher;

import com.TestNice.servlet.eventClass.SignInEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventSignInService {

    private final ApplicationEventPublisher applicationEventPublisher;

    public SignInEvent publishSignInEvent (SignInEvent signInEvent) {
        String result = "";
        applicationEventPublisher.publishEvent(signInEvent);
        return signInEvent;
    }

}
