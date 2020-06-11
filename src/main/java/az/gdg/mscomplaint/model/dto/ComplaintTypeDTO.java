package az.gdg.mscomplaint.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintTypeDTO {
    private Long id;
    private String type;
}
