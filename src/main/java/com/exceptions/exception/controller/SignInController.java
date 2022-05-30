package com.exceptions.exception.controller;

import com.exceptions.exception.exceptions.WrongLoginException;
import com.exceptions.exception.exceptions.WrongPasswordException;
import com.exceptions.exception.service.SignInService;
import com.sun.source.tree.PatternTree;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;


@RestController
public class SignInController {


    @GetMapping(path = "/sign_in")
    public static boolean signIn(@RequestParam(value = "login") String login,
                                 @RequestParam(value = "password") String password,
                                 @RequestParam(value = "confirmPassword") String confirmPassword) {
        try {
            if (login.length() <= 20 && password.length() < 20 && password.equals(confirmPassword)) {
                return true;
            } else if (Pattern.matches("[a-zA-Z0-9_]+", login) && Pattern.matches("[a-zA-Z0-9_]+", password) && Pattern.matches("[a-zA-Z0-9_]+", confirmPassword)) {
                return true;
            }
        } catch (WrongLoginException e) {
            if (login.length() > 20)
                return false;
        } catch (WrongPasswordException e) {
            if (password.length() > 20 || !password.equals(confirmPassword)) {
                return false;
            }
        }
    }
}
