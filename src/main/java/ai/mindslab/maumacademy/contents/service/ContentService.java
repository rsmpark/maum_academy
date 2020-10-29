package ai.mindslab.maumacademy.contents.service;

import ai.mindslab.maumacademy.contents.domain.Content;
import ai.mindslab.maumacademy.contents.domain.ContentFile;

import java.util.List;

public interface ContentService{
    List<Content> getContentsByCourseId(int id);

    List<ContentFile> getContentFilesByContentId(int id);
}
