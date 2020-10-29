package ai.mindslab.maumacademy.common.service;

import ai.mindslab.maumacademy.contents.domain.Course;

import java.util.List;

public interface HomeService {
    public List<Course> getCourseContentsByModuleId(int moduleId);
}
