package az.gdg.mscomplaint.util;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidatorContext;

@NoArgsConstructor
@Component
public class CheckViolationHelper {
    public void addViolation(ConstraintValidatorContext context, String node, String messageTemplate) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messageTemplate).addPropertyNode(node).addConstraintViolation();
    }
}
