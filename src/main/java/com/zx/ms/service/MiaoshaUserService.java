package com.zx.ms.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.zx.ms.dao.MiaoshaUserDao;
import com.zx.ms.domain.MiaoshaUser;
import com.zx.ms.exception.GlobalException;
import com.zx.ms.redis.MiaoshaUserKey;
import com.zx.ms.redis.RedisService;
import com.zx.ms.result.CodeMsg;
import com.zx.ms.util.MD5Util;
import com.zx.ms.util.UUIDUtil;
import com.zx.ms.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MiaoshaUserService {

	@Autowired
	private MiaoshaUserDao miaoshaUserDao;
	@Autowired
	private RedisService redisService;

	public static final String COOKI_NAME_TOKEN="token";

	public MiaoshaUser getById(long id) {
		return miaoshaUserDao.getById(id);
	}


	public boolean login(HttpServletResponse response,LoginVo loginVo) {
		if(loginVo==null){
			throw new GlobalException(CodeMsg.SERVER_ERROR);
		}
		String mobile = loginVo.getMobile();
		String formPass = loginVo.getPassword();
		//判断手机号是否存在
		MiaoshaUser user = getById(Long.parseLong(mobile));
		if(user==null){
			throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
		}
		//验证密码
		String dbPassword = user.getPassword();
		String salt = user.getSalt();
		String calcPass = MD5Util.formPassToDBPass(formPass, salt);
		if(!calcPass.equals(dbPassword)){
			throw new GlobalException(CodeMsg.PASSWORD_ERROR);
		}
		//生成cookie
		String token = UUIDUtil.uuid();
		redisService.set(MiaoshaUserKey.token,token,user);
		Cookie cookie = new Cookie(COOKI_NAME_TOKEN,token);
		cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
		cookie.setPath("/");
		response.addCookie(cookie);
		return true;
	}

	public MiaoshaUser getByToken(String token) {
		if(StringUtils.isEmpty(token)){
			return null;
		}
		return redisService.get(MiaoshaUserKey.token,token,MiaoshaUser.class);
	}
}
