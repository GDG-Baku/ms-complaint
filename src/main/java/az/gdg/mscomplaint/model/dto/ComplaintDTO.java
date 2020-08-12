package az.gdg.mscomplaint.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintDTO {
    private Long id;
    private Long typeId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String message;
    private Long statusId;
}
