package carshop.model.entity;

import carshop.model.enums.Statuses;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Request {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "request_id", nullable = false)
    private Long requestId;
    @Basic
    @Column(name = "advert_id", nullable = false)
    private Long advertId;
    @Basic
    @Column(name = "email", nullable = false, length = 45)
    private String email;
    @Basic
    @Column(name = "date", nullable = false)
    private Date date;
    @Basic
    @Column(name = "status", nullable = false, length = 45)
    private String status;
}
