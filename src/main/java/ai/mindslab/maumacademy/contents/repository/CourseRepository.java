package ai.mindslab.maumacademy.contents.repository;

import ai.mindslab.maumacademy.contents.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {

    List<Course> getAllByModuleId(int ModuleId);
}
