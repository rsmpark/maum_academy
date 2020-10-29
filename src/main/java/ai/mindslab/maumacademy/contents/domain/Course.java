package ai.mindslab.maumacademy.contents.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "int(3)")
    private int id;

    @Column(name ="name", columnDefinition = "varchar(50)")
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="module_id", nullable=false)
    private Module module;

    @OneToMany(mappedBy="course")
    private List<Content> contents;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", module=" + module +
                '}';
    }
}
