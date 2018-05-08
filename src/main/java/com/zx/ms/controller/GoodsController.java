package com.zx.ms.controller;

import com.zx.ms.domain.MiaoshaUser;
import com.zx.ms.redis.RedisService;
import com.zx.ms.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;


@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private MiaoshaUserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/to_list")
    public String list(Model model,
       @CookieValue(value = MiaoshaUserService.COOKI_NAME_TOKEN, required = false) String cookieToken,
       @RequestParam(value = MiaoshaUserService.COOKI_NAME_TOKEN, required = false) String paramToken) {
        if(StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken)){
            return "login";
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        MiaoshaUser user = userService.getByToken(token);
        model.addAttribute("user", user);
        return "goods_list";
    }

}
