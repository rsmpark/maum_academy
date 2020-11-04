package ai.mindslab.maumacademy.contents.domain;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "media_attach")
public class MediaAttach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "int(3)")
    private int id;

    @Column(name ="file_type", columnDefinition = "varchar(10)")
    private String fileType;

    @Column(name="url", columnDefinition = "varchar(100)")
    private String url;

    @Column(name = "page_num", columnDefinition = "int(3)")
    private int pageNum;

    @Column(name="page_title", columnDefinition = "varchar(100)")
    private String pageTitle;

    @Column(name="heading", columnDefinition = "varchar(50)")
    private String heading;

    @Column(name="subheading", columnDefinition = "varchar(50)")
    private String subheading;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="file_id", nullable=false)
    private ContentFile contentFile;

    public int getContentId(){
        return contentFile.getContent().getId();
    }
}
