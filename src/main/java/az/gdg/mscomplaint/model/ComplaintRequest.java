package az.gdg.mscomplaint.model;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintRequest {
    private int typeId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String message;
}
