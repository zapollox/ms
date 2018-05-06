package com.zx.ms.controller;

import com.zx.ms.domain.User;
import com.zx.ms.redis.RedisConfig;
import com.zx.ms.redis.RedisService;
import com.zx.ms.redis.UserKey;
import com.zx.ms.result.Result;
import com.zx.ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DELL on 2018/4/17.
 */
@Controller
@RequestMapping("/demo")
public class SampleController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","test");
        return "hello";
    }
    @RequestMapping("getUser")
    @ResponseBody
    public Result<User> getUser(){
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("redisGet")
    @ResponseBody
    public Result<User> redisGet(){
        User ve = redisService.get(UserKey.getById,""+1, User.class);
        return Result.success(ve);
    }

    @RequestMapping("redisSet")
    @ResponseBody
    public Result<Boolean> redisSet(){
        User user = new User(1,"11111");
        boolean v1 = redisService.set(UserKey.getById,""+1, user);
        return Result.success(v1);
    }

}
