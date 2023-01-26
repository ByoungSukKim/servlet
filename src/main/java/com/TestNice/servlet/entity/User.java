package com.TestNice.servlet.entity;

import lombok.Data;

@Data
public class User {

    private String id;
    private String password;
    private String userName;
    private String kakao;
    private String naver;
    private String google;
    private String facebook;
    private String phone;

}
