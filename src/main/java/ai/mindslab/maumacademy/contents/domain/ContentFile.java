package ai.mindslab.maumacademy.contents.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "content_file")
public class ContentFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "int(3)")
    private int id;

    @Column(name ="name", columnDefinition = "varchar(50)")
    private String name;

    @Column(name="url")
    private String url;

    @OneToOne
    @JoinColumn(name="type")
    private ContentType type;

    @ManyToOne
    @JoinColumn(name="content_id", nullable=false)
    private Content content;
}
