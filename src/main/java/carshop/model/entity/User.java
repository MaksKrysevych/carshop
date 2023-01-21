package carshop.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "email", nullable = false, length = 45)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 500)
    private String password;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic
    @Column(name = "surname", nullable = false, length = 45)
    private String surname;
    @Basic
    @Column(name = "role", nullable = false, length = 45)
    private String role;
    @Basic
    @Column(name = "phone", nullable = false, length = 10)
    private String phone;
    @Basic
    @Column(name = "account", nullable = false)
    private Integer account;
}
