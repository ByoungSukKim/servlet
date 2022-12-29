package com.TestNice.servlet.repository;

import com.TestNice.servlet.entity.Kakao;
import com.TestNice.servlet.entity.Login;
import com.TestNice.servlet.eventClass.LoginEvent;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface LoginRepository {

    Login addAccount(Login login);
    Optional<Login> loginAccount (Login login);
    Optional<LoginEvent> loginAccountEvent (LoginEvent event);
    Optional<Kakao> OauthloginAccount (Kakao paramK);
}
