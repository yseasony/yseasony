package com.yy.action.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.yy.model.Resource;

public class ResourceValidator implements Validator{

	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		 return Resource.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "position", "排序号不能为空!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "resourceName", "资源名不能为空!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "resourceType", "资源类型不能为空!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "资源地址不能为空!");
	}

}
