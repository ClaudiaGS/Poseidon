package com.nnk.springboot.security;

import com.google.common.base.Joiner;
import com.nnk.springboot.repositories.ValidPassword;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;


public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    
    @Override
    public void initialize(ValidPassword arg0) {
    }
    
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8,200),
                new UppercaseCharacterRule(1),
                new DigitCharacterRule(1),
                new SpecialCharacterRule(1)));
        
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
   
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                Joiner.on(",").join(validator.getMessages(result)))
                .addConstraintViolation();
        return false;
    }

}
