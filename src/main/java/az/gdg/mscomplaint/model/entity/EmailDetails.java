package az.gdg.mscomplaint.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EmailDetails {

    private String emailTo;
    private String emailSubject;
    private String emailCC;
    private String emailBody;

}