package com.TestNice.servlet.service;

import com.TestNice.servlet.entity.Login;
import com.TestNice.servlet.eventClass.LoginEvent;
import com.TestNice.servlet.repository.JDBCLoginRepository;
import com.TestNice.servlet.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    @Autowired
    @Qualifier("JDBCLoginRepository")
    private final JDBCLoginRepository loginRepository;


    public Optional<Login> LoginAccount (Login login) {
        return loginRepository.loginAccount(login);
    }

    /*public void LoginAccount (LoginEvent event) {
        applicationEventPublisher.publishEvent(event);
    }*/

}
