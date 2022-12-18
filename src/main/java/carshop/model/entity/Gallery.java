package carshop.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Gallery {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "photo_id", nullable = false)
    private Long photoId;
    @Basic
    @Column(name = "car_id", nullable = false)
    private Long carId;
    @Basic
    @Column(name = "photo", nullable = false, length = 500)
    private String photo;
}
