package com.xavier.system.controller;

import com.xavier.system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Xavier.wu
 * @CreateTime: 2023-04-24  15:36
 * @Description: TODO
 * @Version: 1.0
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;


    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
         return "login";
    }

    @RequestMapping("/logout_success")
    public String logout() {
        return "logout_success";
    }

    @RequestMapping("/login_fail")
    public String logFail(HttpServletRequest request, Model model) {
        request.setAttribute("errorMessage", loginService.determineErrorType(request));
        return "login_fail";
    }



}
