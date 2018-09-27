package com.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.vo.InUser;

@Component
public class EmailValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return InUser.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		InUser user = (InUser) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email is empty");
		if (!user.getEmail().contains("@")) {
			errors.rejectValue("email", "", "Email is not valid.");
		}
	}
}