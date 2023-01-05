package com.TestNice.servlet.eventClass;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class LoginEvent extends ApplicationEvent {

    private String id;
    private String pw;

    public LoginEvent(Object source) {

        super(source);

    }

    public LoginEvent(Object source, String id, String pw) {
        super(source);
        this.id = id;
        this.pw = pw;
    }
}