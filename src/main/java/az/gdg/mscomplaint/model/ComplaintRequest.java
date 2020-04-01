package az.gdg.mscomplaint.model;


import az.gdg.mscomplaint.validation.complaint.ComplaintConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ComplaintConstraint
public class ComplaintRequest {
    private Integer typeId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String message;
}
