package ai.mindslab.maumacademy.common.service;

import ai.mindslab.maumacademy.contents.domain.Course;
import ai.mindslab.maumacademy.contents.repository.ContentRepository;
import ai.mindslab.maumacademy.contents.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService{
    @Autowired
    ContentRepository contentRepository;

    @Autowired
    CourseRepository courseRepository;


    @Override
    public List<Course> getCourseContentsByModuleId(int moduleId) {
        List<Course> courses =  courseRepository.getAllByModuleId(moduleId);

        return courses;
    }
}
