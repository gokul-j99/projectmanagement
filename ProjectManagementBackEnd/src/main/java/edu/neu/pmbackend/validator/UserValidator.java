/**
 * 
 */
package edu.neu.pmbackend.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.neu.pmbackend.entity.User;



/**
 * @author gokuljayavel
 *
 */

@Component
public class UserValidator implements Validator{

	
	
	
	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
	 private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);


	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		User user = (User) target;

        if (user.getPassword().length() < 8) {
            errors.rejectValue("password", "Length", "Password must be at least 8 characters");
        }
        
        

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Match", "Passwords must match");

        }
        

        if (!pattern.matcher(user.getPassword()).matches()) {
            errors.rejectValue("password", "field.pattern", "Password must contain at least one uppercase letter, one lowercase letter, and one digit");
        }
		
	}

}
