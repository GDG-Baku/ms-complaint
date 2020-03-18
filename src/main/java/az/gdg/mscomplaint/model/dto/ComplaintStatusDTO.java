package az.gdg.mscomplaint.model.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintStatusDTO {
    private Integer id;
    private String status;
}
