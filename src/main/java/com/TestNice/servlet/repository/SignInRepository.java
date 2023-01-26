package com.TestNice.servlet.repository;

import com.TestNice.servlet.entity.Kakao;
import com.TestNice.servlet.eventClass.SignInEvent;

public interface SignInRepository {

    String signInUser (SignInEvent event);

}
