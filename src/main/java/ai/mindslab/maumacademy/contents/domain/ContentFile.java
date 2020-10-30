package ai.mindslab.maumacademy.contents.domain;

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
@Table(name = "content_file")
public class ContentFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "int(3)")
    private int id;

    @Column(name ="file_type", columnDefinition = "varchar(50)")
    private String fileType;

    @Column(name="url")
    private String url;

    @OneToOne
    @JoinColumn(name="type")
    private ContentType type;

    @Column(name ="has_audio", columnDefinition = "varchar(1)")
    private String hasAudio = "n";

    @Column(name ="has_video", columnDefinition = "varchar(1)")
    private String hasVideo = "n";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="content_id", nullable=false)
    private Content content;
}
