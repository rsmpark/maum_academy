package ai.mindslab.maumacademy.common.controller;

import ai.mindslab.maumacademy.common.service.HomeService;
import ai.mindslab.maumacademy.contents.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    HomeService homeService;

    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();

        List<Course> courses1 = homeService.getCourseContentsByModuleId(1);
        List<Course> courses2 = homeService.getCourseContentsByModuleId(2);
        List<Course> courses3 = homeService.getCourseContentsByModuleId(3);


        modelAndView.addObject("courses1", courses1);
        modelAndView.addObject("courses2", courses2);
        modelAndView.addObject("courses3", courses3);
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
