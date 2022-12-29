package com.TestNice.servlet.eventPublisher;

import com.TestNice.servlet.eventClass.LoginEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventLoginService {

    private final ApplicationEventPublisher applicationEventPublisher;

    /*public EventLoginService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }*/

    public LoginEvent publishLoginEvent(String id, String pw) {
        LoginEvent loginEvent = new LoginEvent(this,id,pw);
        applicationEventPublisher.publishEvent(loginEvent);

        //리턴값 셋팅
        LoginEvent resultLoginEvent = new LoginEvent(this);
        System.out.println("loginEvent.getId() = " + loginEvent.getId());
        resultLoginEvent.setId(loginEvent.getId());
        System.out.println("loginEvent.getPw() = " + loginEvent.getPw());
        resultLoginEvent.setPw(loginEvent.getPw());

        return resultLoginEvent;

    }
}
