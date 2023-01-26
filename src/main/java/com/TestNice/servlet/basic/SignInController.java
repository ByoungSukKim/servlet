package com.TestNice.servlet.basic;

import com.TestNice.servlet.entity.User;
import com.TestNice.servlet.eventClass.LoginEvent;
import com.TestNice.servlet.eventClass.SignInEvent;
import com.TestNice.servlet.eventListener.EventLoginListener;
import com.TestNice.servlet.eventListener.EventSignInListener;
import com.TestNice.servlet.eventPublisher.EventSignInService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@AllArgsConstructor
@RestController
public class SignInController {

    private EventSignInService eventSignInService;


    @ResponseBody
    @PostMapping("/signIn")
    public int signIn(@RequestBody User user) {
        System.out.println("signIn 메소드");
        SignInEvent signInEvent = new SignInEvent(this);
        signInEvent.setId(user.getId());
        signInEvent.setPassword(user.getPassword());
        signInEvent.setUserName(user.getUserName());
        signInEvent.setKakao(user.getKakao());
        signInEvent.setNaver(user.getNaver());
        signInEvent.setGoogle(user.getGoogle());
        signInEvent.setFacebook(user.getFacebook());
        signInEvent.setPhone(user.getPhone());
        eventSignInService.publishSignInEvent(signInEvent);
        int result = HttpStatus.OK.value();
        return result;
    }
}
