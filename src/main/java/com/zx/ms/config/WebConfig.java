package com.zx.ms.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter{
	
	@Autowired
	private UserArgumentResolver userArgumentResolver;

	//spring mvc 给controller参数赋值时回调的方法
	// (类似一个小仓库,前台传来的数据放在argumentResolvers,在给controller参数赋值
	// 时遍历argumentResolvers找到对应类型的值)
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		//添加根据前台传来的cookie获得到的user信息
		argumentResolvers.add(userArgumentResolver);
	}
	
	
}
