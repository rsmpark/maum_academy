package ai.mindslab.maumacademy.contents.controller;

import ai.mindslab.maumacademy.contents.domain.Content;
import ai.mindslab.maumacademy.contents.domain.ContentFile;
import ai.mindslab.maumacademy.contents.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    ContentService contentService;

    @RequestMapping("")
    public ModelAndView getContent(@RequestParam(name = "id") Integer contentId,
                                   @RequestParam(name = "course") Integer courseId, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
//        if(request.getSession().getAttribute("user") == null){
//            modelAndView.setViewName("redirect:/");
//            return modelAndView;
//        }


        final List<Content> relatedContents = contentService.getContentsByCourseId(courseId);

        List<Content> filteredContent = relatedContents.stream().filter(content -> {
            if (content.getId() == contentId) {
                int selectedIndex = relatedContents.indexOf(content) + 1;
                modelAndView.addObject("selectedIndex", selectedIndex);
                modelAndView.addObject("selectedContent", content);
            }

            return !(content.getId() == contentId);
        }).collect(Collectors.toList());


        modelAndView.addObject("relatedContents", filteredContent);

        List<ContentFile> contentFiles = contentService.getContentFilesByContentId(contentId);
        contentService.mapContentFilesToModel(contentFiles,contentId, modelAndView);



        modelAndView.setViewName("content");
        return modelAndView;
    }
}
