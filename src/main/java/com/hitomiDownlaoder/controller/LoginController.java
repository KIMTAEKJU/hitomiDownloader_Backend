package com.hitomiDownlaoder.controller;

import com.hitomiDownlaoder.service.login.LoginServiceImpl;
import com.hitomiDownlaoder.service.userInfo.UserInfoServiceImpl;
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

    @Autowired
    private UserInfoServiceImpl userInfoService;

    @GetMapping(value = "/login/kakao")
    @ResponseBody
    public Map<String, Object> loginKaKao(String code){
        System.out.println("code: " + code);
        String accessToken = loginService.getAccessToken(code);
        return userInfoService.getUserInfo(accessToken);
    }
}
