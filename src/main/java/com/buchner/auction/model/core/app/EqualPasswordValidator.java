package com.buchner.auction.model.core.app;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("passwordConfirm")
public class EqualPasswordValidator implements Validator {

    public EqualPasswordValidator() {

    }

    @Override public void validate(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {

        String password = (String) value;
        UIInput passwordRepeatComponent =
            (UIInput) component.getAttributes().get("equalPassword");
        String submittedValue = (String) passwordRepeatComponent.getSubmittedValue();

        if (password == null || password.isEmpty() || submittedValue == null || submittedValue.isEmpty()) {
            return;
        }

        if (!password.equals(submittedValue)) {
            passwordRepeatComponent.setValid(false);
            throw new ValidatorException(new FacesMessage("Passwords are not equal."));
        }

    }
}
