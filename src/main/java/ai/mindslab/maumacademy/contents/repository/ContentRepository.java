package ai.mindslab.maumacademy.contents.repository;

import ai.mindslab.maumacademy.contents.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content,Long> {
    List<Content> getAllByCourseId (int courseId);

    Content getById (int contentId);
}
