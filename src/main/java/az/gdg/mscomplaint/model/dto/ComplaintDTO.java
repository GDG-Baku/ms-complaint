package az.gdg.mscomplaint.model.dto;
import az.gdg.mscomplaint.model.entity.ComplaintType;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintDTO {
    private Integer id;
    private int typeId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String message;
    private int statusId;
}
