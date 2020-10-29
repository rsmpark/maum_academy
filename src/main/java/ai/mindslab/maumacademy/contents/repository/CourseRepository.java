package ai.mindslab.maumacademy.contents.repository;

import ai.mindslab.maumacademy.contents.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> getAllByModuleId(int ModuleId);
}
