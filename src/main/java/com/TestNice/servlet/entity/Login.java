package com.TestNice.servlet.entity;

import lombok.Data;

@Data
public class Login {

    private String id;
    private String password;

    public Login() {

    }

    public Login(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
