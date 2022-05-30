package com.exceptions.exception.service;


import org.springframework.stereotype.Service;

@Service
public class SignInServiceServiceImpl implements SignInService {


    @Override
    public String signIn(String login, String password, String confirmPassword) {
return "Enter login " + login + "\n" +
        "Enter password " + password + "\n" +
        "Confirm password " + confirmPassword;
    }
}
