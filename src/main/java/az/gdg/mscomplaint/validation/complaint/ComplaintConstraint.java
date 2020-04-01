package az.gdg.mscomplaint.validation.complaint;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.FIELD, ElementType.TYPE})
@Constraint(validatedBy = ComplaintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ComplaintConstraint {
    String message() default "Complaint request validation error";

    Class[] groups() default {};

    Class[] payload() default {};

    String[] sortingFields() default {};
}