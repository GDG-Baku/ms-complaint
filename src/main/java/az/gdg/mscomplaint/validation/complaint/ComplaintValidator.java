package az.gdg.mscomplaint.validation.complaint;

import az.gdg.mscomplaint.model.ComplaintRequest;
import az.gdg.mscomplaint.util.CheckViolationHelper;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ComplaintValidator implements
        ConstraintValidator<ComplaintConstraint, ComplaintRequest> {

    private final CheckViolationHelper checkViolationHelper;

    public ComplaintValidator(CheckViolationHelper checkViolationHelper) {
        this.checkViolationHelper = checkViolationHelper;
    }

    @Override
    public boolean isValid(ComplaintRequest complaintRequest, ConstraintValidatorContext context) {
        return complaintRequest != null &&
                isNameValid(complaintRequest.getName(), context) &&
                isSurnameValid(complaintRequest.getSurname(), context) &&
                isEmailValid(complaintRequest.getEmail(), context) &&
                isMessageValid(complaintRequest.getMessage(), context) &&
                isTypeId(complaintRequest.getTypeId(), context);
    }

    private boolean isNameValid(String name, ConstraintValidatorContext context) {
        if (name == null || name.isEmpty()) {
            checkViolationHelper.addViolation(context, name, "Name cannot be empty");
            return false;
        }
        return true;
    }

    private boolean isSurnameValid(String surname, ConstraintValidatorContext context) {
        if (surname == null || surname.isEmpty()) {
            checkViolationHelper.addViolation(context, surname, "Surname cannot be empty");
            return false;
        }
        return true;
    }

    private boolean isEmailValid(String email, ConstraintValidatorContext context) {
        if (email == null || !email.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?") || email.length() > 255) {
            checkViolationHelper.addViolation(context, "email", "Email is not valid");
            return false;
        }
        return true;
    }

    private boolean isMessageValid(String message, ConstraintValidatorContext context) {
        if (message == null || message.isEmpty()) {
            checkViolationHelper.addViolation(context, "message", "Message cannot be empty");
            return false;
        }
        return true;
    }

    private boolean isTypeId(Integer typeId, ConstraintValidatorContext context) {
        if (typeId == null) {
            checkViolationHelper.addViolation(context, "typeId", "Type cannot be null");
            return false;
        }
        return true;
    }
}