package carshop.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Car {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "car_id", nullable = false)
    private Long carId;
    @Basic
    @Column(name = "type", nullable = false, length = 45)
    private String type;
    @Basic
    @Column(name = "brand", nullable = false, length = 45)
    private String brand;
    @Basic
    @Column(name = "model", nullable = false, length = 45)
    private String model;
    @Basic
    @Column(name = "kit", nullable = false, length = 45)
    private String kit;
    @Basic
    @Column(name = "graduation_year", nullable = false)
    private Date graduationYear;
}
