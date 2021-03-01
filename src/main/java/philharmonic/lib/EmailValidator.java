package philharmonic.lib;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
    private static final String EMAIL_REGEX = "[\\w\\.]+@[a-z]+\\.[a-z]+";

    @Override
    public boolean isValid(String emailField, ConstraintValidatorContext cvc) {
        return emailField != null && emailField.matches(EMAIL_REGEX);
    }
}
