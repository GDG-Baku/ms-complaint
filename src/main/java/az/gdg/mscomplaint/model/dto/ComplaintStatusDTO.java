package az.gdg.mscomplaint.model.dto;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintStatusDTO {
    private Integer id;
    private String status;
}
