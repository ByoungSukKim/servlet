package com.TestNice.servlet.eventClass;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
@Setter
public class SignInEvent extends ApplicationEvent {

    private String id;
    private String password;
    private String userName;
    private String kakao;
    private String naver;
    private String google;
    private String facebook;
    private String phone;
    private String result;

    public SignInEvent(Object source) {
        super(source);
    }

    public SignInEvent(Object source, String id, String password, String userName, String kakao, String naver
            , String google, String facebook, String phone, String result) {
        super(source);
        this.id = id;
        this.password = password;
        this.userName = userName;
        this.kakao = kakao;
        this.naver = naver;
        this.google = google;
        this.facebook = facebook;
        this.phone = phone;
        this.result = result;
    }
}
