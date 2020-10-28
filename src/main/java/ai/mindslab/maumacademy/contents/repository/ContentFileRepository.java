package ai.mindslab.maumacademy.contents.repository;

import ai.mindslab.maumacademy.contents.domain.ContentFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentFileRepository extends JpaRepository<ContentFile,Long> {
    ContentFile getByContentId(int contentId);
}
