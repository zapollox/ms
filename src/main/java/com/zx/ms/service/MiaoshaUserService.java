package com.zx.ms.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.zx.ms.dao.MiaoshaUserDao;
import com.zx.ms.domain.MiaoshaUser;
import com.zx.ms.redis.RedisService;
import com.zx.ms.result.CodeMsg;
import com.zx.ms.util.MD5Util;
import com.zx.ms.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MiaoshaUserService {

	@Autowired
	private MiaoshaUserDao miaoshaUserDao;

	public MiaoshaUser getById(long id) {
		return miaoshaUserDao.getById(id);
	}


	public CodeMsg login(LoginVo loginVo) {
		if(loginVo==null){
			return CodeMsg.SERVER_ERROR;
		}
		String mobile = loginVo.getMobile();
		String formPass = loginVo.getPassword();
		//判断手机号是否存在
		MiaoshaUser user = getById(Long.parseLong(mobile));
		if(user==null){
			return CodeMsg.MOBILE_NOT_EXIST;
		}
		//验证密码
		String dbPassword = user.getPassword();
		String salt = user.getSalt();
		String calcPass = MD5Util.formPassToDBPass(formPass, salt);
		if(!calcPass.equals(dbPassword)){
			return CodeMsg.PASSWORD_ERROR;
		}
		return CodeMsg.SUCCESS;
	}
}
