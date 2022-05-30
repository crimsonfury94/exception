package com.exceptions.exception.controller;

import com.exceptions.exception.exceptions.WrongLoginException;
import com.exceptions.exception.exceptions.WrongPasswordException;
import com.exceptions.exception.service.SignInService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;


@RestController
@RequestMapping(path = "/sign_in")
public class SignInController {


    private final SignInService signInService;

    public SignInController(SignInService signInService) {
        this.signInService = signInService;
    }


    @GetMapping(path = "/")
    public static String signInAccount(@RequestParam(value = "login") String login,
                                       @RequestParam(value = "password") String password,
                                       @RequestParam(value = "confirmPassword") String confirmPassword) {

        try {
            if ((login.length() <= 20 && Pattern.matches("[a-zA-Z0-9_]+", login)) || (password.length() < 20 && Pattern.matches("[a-zA-Z0-9_]+", password)) || (password.equals(confirmPassword) && Pattern.matches("[a-zA-Z0-9_]+", confirmPassword))) {
                return "true";
            } //else if (Pattern.matches("[a-zA-Z0-9_]+", login) && Pattern.matches("[a-zA-Z0-9_]+", password) || Pattern.matches("[a-zA-Z0-9_]+", confirmPassword)) {
            // return "true";
            // }
        } catch (WrongPasswordException e) {
            if (password.length() > 20 || !password.equals(confirmPassword))
                return "false";
        } catch (WrongLoginException e) {
            if (login.length() > 20)
                return "false";
        }
        return "Введите данные";
    }


    @GetMapping(path = "/welcome")
    public String signIn(@RequestParam(value = "login") String login,
                         @RequestParam(value = "password") String password,
                         @RequestParam(value = "confirmPassword") String confirmPassword) {

        return signInService.signIn(login, password, confirmPassword);
    }
}
