package ai.mindslab.maumacademy.contents.service;

import ai.mindslab.maumacademy.contents.domain.Content;
import ai.mindslab.maumacademy.contents.domain.ContentFile;
import ai.mindslab.maumacademy.contents.repository.ContentFileRepository;
import ai.mindslab.maumacademy.contents.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    ContentRepository contentRepository;

    @Autowired
    ContentFileRepository contentFileRepository;

    @Override
    public List<Content> getContentsByCourseId(int id) {
        return contentRepository.getContentByCourseId(id);
    }

    @Override
    public List<ContentFile> getContentFilesByContentId(int id) {
        return contentFileRepository.getAllByContentId(id);
    }
}
