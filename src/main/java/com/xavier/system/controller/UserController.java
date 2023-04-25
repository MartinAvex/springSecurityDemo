package com.xavier.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Xavier.wu
 * @CreateTime: 2023-04-24  17:03
 * @Description: TODO
 * @Version: 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/index")
    public String index() {
        return "/user/index";
    }

}
