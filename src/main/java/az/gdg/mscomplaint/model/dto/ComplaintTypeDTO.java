package az.gdg.mscomplaint.model.dto;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintTypeDTO {
    private Integer id;
    private String type;
}
