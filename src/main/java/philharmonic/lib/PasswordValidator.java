package philharmonic.lib;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, Object> {
    private String password;
    private String repeatPassword;

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
        this.password = constraintAnnotation.field();
        this.repeatPassword = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext cvc) {
        Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(password);
        Object repeatPasswordValue = new BeanWrapperImpl(value).getPropertyValue(repeatPassword);
        if (String.valueOf(passwordValue).isEmpty() || passwordValue == null) {
            return false;
        }
        return passwordValue.equals(repeatPasswordValue);
    }
}
