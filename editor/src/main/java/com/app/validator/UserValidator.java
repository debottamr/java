package com.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.vo.InUser;

@Component
public class UserValidator implements Validator  {
	@Override
	public boolean supports(Class<?> clazz) {
		return InUser.class.isAssignableFrom(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		InUser user = (InUser)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "","Username is empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password is empty");
		if (user.getName().length()<5) {
			errors.rejectValue("name","", "Username length is less than 5");
		}
	}
} 