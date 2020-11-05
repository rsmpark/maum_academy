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
@Table(name = "auth")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "int(3)")
    private int id;

    @Column(name ="email", columnDefinition = "varchar(100)")
    private String email;

    @Column(name ="access_token", columnDefinition = "varchar(200)")
    private String access_token;

    @Column(name ="access_expire_time", columnDefinition = "varchar(200)")
    private String access_expire_time;

    @Column(name ="refresh_token", columnDefinition = "varchar(200)")
    private String refresh_token;

    @Column(name ="refresh_expire_time", columnDefinition = "varchar(200)")
    private String refresh_expire_time;



    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", access_token='" + access_token + '\'' +
                ", access_expire_time=" + access_expire_time + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", refresh_expire_time='" + refresh_expire_time + '\'' +
                '}';
    }
}
