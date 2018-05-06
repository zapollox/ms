package com.zx.ms.controller;

import com.zx.ms.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DELL on 2018/5/6.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(){
        return null;
    }
}
