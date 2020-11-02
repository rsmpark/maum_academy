package ai.mindslab.maumacademy.contents.service;

import ai.mindslab.maumacademy.contents.domain.Content;
import ai.mindslab.maumacademy.contents.domain.ContentFile;
import ai.mindslab.maumacademy.contents.domain.MediaAttach;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface ContentService{
    List<Content> getContentsByCourseId(int id);

    List<ContentFile> getContentFilesByContentId(int id);

    void mapContentFilesToModel(List<ContentFile> contentFiles, int contentId, ModelAndView modelAndView);
}
