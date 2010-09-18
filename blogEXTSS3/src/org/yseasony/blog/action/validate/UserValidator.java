package org.yseasony.blog.action.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.yseasony.blog.entity.User;


public class UserValidator implements Validator {

	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz) {
		 return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginname", "用户名不能为空!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "密码不能为空!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "姓名不能为空!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "用户状态不能为空!");
	}

}
