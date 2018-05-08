package com.zx.ms.controller;

import com.zx.ms.result.CodeMsg;
import com.zx.ms.result.Result;
import com.zx.ms.service.MiaoshaUserService;
import com.zx.ms.util.ValidatorUtil;
import com.zx.ms.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;
import org.thymeleaf.util.Validate;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by DELL on 2018/5/6.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private MiaoshaUserService miaoshaUserService;

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response,@Valid LoginVo loginVo){
        log.info(loginVo.toString());
        //登陆
        miaoshaUserService.login(response,loginVo);
        return Result.success(true);
    }
}
