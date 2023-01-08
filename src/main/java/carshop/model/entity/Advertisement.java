package carshop.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Advertisement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "advert_id", nullable = false)
    private Long advertId;
    @Basic
    @Column(name = "car_id", nullable = false)
    private Long carId;
    @Basic
    @Column(name = "photo_id", nullable = false)
    private Long photoId;
    @Basic
    @Column(name = "price", nullable = false)
    private int price;
    @Basic
    @Column(name = "description", nullable = false, length = 500)
    private String description;
}
