package az.gdg.mscomplaint.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "message")
    private String message;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at")
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ComplaintTypeEntity typeId;

    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @ManyToOne
    private ComplaintStatusEntity statusId;
}
