package com.TestNice.servlet.eventListener;

import com.TestNice.servlet.entity.Login;
import com.TestNice.servlet.eventClass.LoginEvent;
import com.TestNice.servlet.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EventLoginListener implements ApplicationListener<LoginEvent> {

    private final LoginRepository loginRepository;

    @Autowired
    public EventLoginListener(LoginRepository loginRepository) {

        this.loginRepository = loginRepository;

    }


    @Async
    @EventListener
    public void onApplicationEvent(LoginEvent event) {

        Optional<LoginEvent> loginEventResult = loginRepository.loginAccountEvent(event);
        LoginEvent loginEvent = loginEventResult.get();
        String id = loginEvent.getId();
        String pw = loginEvent.getPw();

        event.setId(id);
        event.setPw(pw);
    }
}
