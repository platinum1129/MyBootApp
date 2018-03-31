package jp.co.corerd.springboot.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.co.corerd.springboot.annotation.Phone;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	private boolean onlyNumber = false;
	
	@Override
	public void initialize(Phone phone) {
		onlyNumber = phone.onlyNumber();
	}
	
	@Override
	public boolean isValid(String input, ConstraintValidatorContext context) {
		
		if (input == null) {
			return false;
		}
		String pattern = null;
		if (onlyNumber) {
			pattern = "[0-9]*";
		} else {
			pattern = "[0-9()-]*";
		}
		
		return input.matches(pattern);
	}
	
}
