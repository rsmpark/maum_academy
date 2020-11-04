package ai.mindslab.maumacademy.contents.repository;

import ai.mindslab.maumacademy.contents.domain.MediaAttach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaAttachRepository extends JpaRepository<MediaAttach, Integer> {
    @Query("SELECT ma FROM MediaAttach ma JOIN ContentFile cf ON ma.contentFile.id = cf.id " +
            "WHERE cf.content.id = ?1  AND ma.fileType ='audio' order by ma.pageNum asc ")
    List<MediaAttach> getAudioAttachByContentId(int id);

    @Query("SELECT ma FROM MediaAttach ma JOIN ContentFile cf ON ma.contentFile.id = cf.id " +
            "WHERE cf.content.id = ?1  AND ma.fileType ='video'")
    List<MediaAttach> getVideoAttachByContentId(int id);
}
