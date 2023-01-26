package com.TestNice.servlet.eventListener;

import com.TestNice.servlet.eventClass.LoginEvent;
import com.TestNice.servlet.eventClass.SignInEvent;
import com.TestNice.servlet.repository.UserSignInRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class EventSignInListener {

    private final UserSignInRepository userSignInRepository;

    @Async
    /*@Order(1)*/
    @EventListener
    public void onApplicationEvent(SignInEvent event) {

        System.out.println("------ onApplicationEvent = 1 Start ------" );

        String result = userSignInRepository.signInUser(event);

        SignInEvent signInEvent = new SignInEvent(this);
        signInEvent.setResult(result);

        System.out.println("------ onApplicationEvent = 1 End ------" );
    }
}
