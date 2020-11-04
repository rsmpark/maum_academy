package ai.mindslab.maumacademy.contents.repository;

import ai.mindslab.maumacademy.contents.domain.ContentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentFileRepository extends JpaRepository<ContentFile,Integer> {
    List<ContentFile> getAllByContentId(int contentId);
}
