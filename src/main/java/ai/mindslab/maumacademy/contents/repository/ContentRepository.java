package ai.mindslab.maumacademy.contents.repository;

import ai.mindslab.maumacademy.contents.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content,Integer> {
    @Query("select c from Content c where c.course.id = ?1")
    List<Content> getContentByCourseId (int courseId);

}
