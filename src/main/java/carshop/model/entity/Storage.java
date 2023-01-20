package carshop.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Storage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "advert_id", nullable = false)
    private Long advertId;
    @Basic
    @Column(name = "status", nullable = false, length = 45)
    private String status;
}