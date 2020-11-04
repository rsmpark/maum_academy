package ai.mindslab.maumacademy.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "int(3)")
    private int userNo;

    @Column(name ="name", columnDefinition = "varchar(100)")
    private String name;

    @Column(name ="email", columnDefinition = "varchar(100)")
    private String email;

    @Column(name ="phone", columnDefinition = "varchar(200)")
    private String phone;
}
