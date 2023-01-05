package com.TestNice.servlet.eventListener;

import com.TestNice.servlet.eventClass.LoginEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EventEmailListener {


    @Async
    @Order(3)
    @EventListener
    public void AppricationEmailListner(LoginEvent event){

        System.out.println("------ onApplicationEvent = 3 End ------");

    }
}
