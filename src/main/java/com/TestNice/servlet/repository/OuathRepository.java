package com.TestNice.servlet.repository;

import com.TestNice.servlet.entity.Kakao;

import java.util.Optional;

public interface OuathRepository {

    Optional<Kakao> OauthloginAccount (Kakao paramK);
}
