package com.zx.ms.validator;
import  javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.zx.ms.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;


public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {//注解名，被修饰的参数类型

	private boolean required = false;//是否可以为空(值是否是必须的)
	
	public void initialize(IsMobile constraintAnnotation) {//初始化方法
		required = constraintAnnotation.required();
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(required) {//值为必须值
			return ValidatorUtil.isMobile(value);
		}else {
			//值不为必须的
			if(StringUtils.isEmpty(value)) {//空是可以的
				return true;
			}else {//不为空则进行判断
				return ValidatorUtil.isMobile(value);
			}
		}
	}

}
