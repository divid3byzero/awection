package com.buchner.auction.model.core.app;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator to check if the password that are entered during
 * user registration are the same.
 */
@FacesValidator("passwordConfirm")
public class EqualPasswordValidator implements Validator {

    public EqualPasswordValidator() {

    }

    @Override public void validate(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {

        // Get value from password field.
        String password = (String) value;

        // Get value bound to "equalPassword" element. This is the
        // value that was entered into the "repeat-password-field"
        UIInput passwordRepeatComponent =
            (UIInput) component.getAttributes().get("equalPassword");
        String submittedValue = (String) passwordRepeatComponent.getSubmittedValue();

        // If the values are empty, let JSF do it's work. Since the input fields are marked as
        // required, they will be marked as faulty by the framwork.
        if (password == null || password.isEmpty() || submittedValue == null || submittedValue.isEmpty()) {
            return;
        }

        // This is the check if the entered passwords are the same.
        // If they are not the same, a ValidatorException is thrown which tells JSF to mark
        // the input fields as wrong.
        if (!password.equals(submittedValue)) {
            passwordRepeatComponent.setValid(false);
            throw new ValidatorException(new FacesMessage("Passwords are not equal."));
        }

    }
}
