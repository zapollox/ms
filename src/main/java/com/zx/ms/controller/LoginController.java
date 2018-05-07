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
    public Result<Boolean> doLogin(@Valid LoginVo loginVo){
        log.info(loginVo.toString());
//        //参数校验
//        String passInput = loginVo.getPassword();
//        String mobile = loginVo.getMobile();
//        if(StringUtils.isEmpty(passInput)){
//            return Result.error(CodeMsg.PASSWORD_EMPTY);
//        }
//        if(StringUtils.isEmpty(mobile)){
//            return Result.error(CodeMsg.MOBILE_EMPTY);
//        }
//        if(!ValidatorUtil.isMobile(mobile)){
//            return Result.error(CodeMsg.MOBILE_ERROR);
//        }
        //登陆
        CodeMsg cm = miaoshaUserService.login(loginVo);
        if(cm.getCode()==0){
            return Result.success(true);
        }else {
            return Result.error(cm);
        }
    }
}
