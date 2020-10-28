package ai.mindslab.maumacademy.contents.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "content")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "int(3)")
    private int id;

    @Column(name ="name", columnDefinition = "varchar(50)")
    private String name;

    @Column(name ="description", columnDefinition = "varchar(200)")
    private String description;

    @ManyToOne
    @JoinColumn(name="course_id", nullable=false)
    private Course course;

    @OneToMany(mappedBy="content")
    private List<ContentFile> contentFiles;
}
