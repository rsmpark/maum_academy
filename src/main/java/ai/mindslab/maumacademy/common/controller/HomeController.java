package ai.mindslab.maumacademy.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/home")
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response, Model model) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("home");
        return modelAndView;
    }
}
