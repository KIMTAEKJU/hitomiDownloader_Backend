package com.hitomiDownlaoder.controller;

import com.hitomiDownlaoder.service.login.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @GetMapping(value = "/login")
    @ResponseBody
    public String login(String code){
        System.out.println(code);

        loginService.getAccessToken(code);
        return "";
    }
}
