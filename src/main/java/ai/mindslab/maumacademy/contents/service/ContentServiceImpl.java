package ai.mindslab.maumacademy.contents.service;

import ai.mindslab.maumacademy.contents.domain.Content;
import ai.mindslab.maumacademy.contents.domain.ContentFile;
import ai.mindslab.maumacademy.contents.domain.MediaAttach;
import ai.mindslab.maumacademy.contents.repository.ContentFileRepository;
import ai.mindslab.maumacademy.contents.repository.ContentRepository;
import ai.mindslab.maumacademy.contents.repository.MediaAttachRepository;
import ai.mindslab.maumacademy.contents.vo.AttachFileVo;
import ai.mindslab.maumacademy.contents.vo.AttachPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    ContentRepository contentRepository;

    @Autowired
    ContentFileRepository contentFileRepository;

    @Autowired
    MediaAttachRepository mediaAttachRepository;

    @Override
    public List<Content> getContentsByCourseId(int id) {
        return contentRepository.getContentByCourseId(id);
    }

    @Override
    public List<ContentFile> getContentFilesByContentId(int id) {
        return contentFileRepository.getAllByContentId(id);
    }


    @Override
    public void mapContentFilesToModel(List<ContentFile> contentFiles, int contentId, ModelAndView modelAndView) {
        List<String> videoUrls = new ArrayList<>();
        List<String> pdfUrls = new ArrayList<>();

        contentFiles.forEach(contentFile -> {
            if (contentFile.getFileType().equals("pdf")) {
                pdfUrls.add(contentFile.getUrl());
            } else {
                videoUrls.add(contentFile.getUrl());
            }
        });

        List<MediaAttach> audioAttachList = mediaAttachRepository.getAudioAttachByContentId(contentId);
        List<MediaAttach> videoAttachList = mediaAttachRepository.getVideoAttachByContentId(contentId);

//
//        Map<Integer, Map<Object, List<MediaAttach>>> groupedAudioAttach = audioAttachList.stream()
//                .collect(groupingBy(MediaAttach::getContentId,
//                        groupingBy(ma -> AttachFileVo.builder()
//                                .pageTitle(ma.getPageTitle())
//                                .pageNum(ma.getPageNum())
//                                .url(ma.getUrl())
//                                .heading(ma.getHeading())
//                                .subheading(ma.getSubheading())
//                                .build())
//                        )
//                );
//        Map<Integer, Map<List<Object>, Map<AttachFileVo, List<MediaAttach>>>> groupedAudioAttach2 = audioAttachList.stream()
//                .collect(groupingBy(MediaAttach::getContentId,
//                        groupingBy(ma -> Arrays.asList(ma.getPageTitle(), ma.getPageNum(), ma.getHeading()),
//                                groupingBy(ma -> AttachFileVo.builder()
//                                        .url(ma.getUrl())
//                                        .subheading(ma.getSubheading())
//                                        .build())
//                        ))
//                );
//
//        Map<Integer, Map<Object, List<AttachFileVo>>> groupedAudioAttach = audioAttachList.stream()
//                .collect(groupingBy(MediaAttach::getContentId,
//                        groupingBy(ma -> Arrays.asList(ma.getPageTitle(), ma.getPageNum(), ma.getHeading()),
//                                Collectors.mapping(ma -> AttachFileVo.builder()
//                                        .url(ma.getUrl())
//                                        .subheading(ma.getSubheading())
//                                        .build(), Collectors.toList())
//                        ))
//                );
//
//        Map<Integer, Map<AttachPageVo, List<AttachFileVo>>> groupedAudioAttach3 = audioAttachList.stream()
//                .collect(groupingBy(MediaAttach::getContentId,
//                        groupingBy(ma -> AttachPageVo.builder()
//                                        .pageTitle(ma.getPageTitle())
//                                        .pageNum(ma.getPageNum())
//                                        .build(),
//                                Collectors.mapping(ma -> AttachFileVo.builder()
//                                        .url(ma.getUrl())
//                                        .subheading(ma.getSubheading())
//                                        .build(), Collectors.toList())
//                        )));
//        Map<Integer, Map<AttachPageVo, List<AttachFileVo>>> groupedAudioAttach = audioAttachList.stream()
//                .collect(groupingBy(MediaAttach::getContentId,
//                        groupingBy(ma -> AttachPageVo.builder()
//                                        .pageTitle(ma.getPageTitle())
//                                        .pageNum(ma.getPageNum())
//                                        .build(), LinkedHashMap::new,
//                                Collectors.mapping(ma -> AttachFileVo.builder()
//                                        .url(ma.getUrl())
//                                        .subheading(ma.getSubheading())
//                                        .build(), Collectors.toList())
//                        )));

        Map<Integer, Map<AttachPageVo, Map<String, List<AttachFileVo>>>> groupedAudioAttach= audioAttachList.stream()
                .collect(groupingBy(MediaAttach::getContentId,
                        groupingBy(ma -> AttachPageVo.builder()
                                        .pageTitle(ma.getPageTitle())
                                        .pageNum(ma.getPageNum())
                                        .build(), LinkedHashMap::new,
                                groupingBy(ma -> ma.getHeading(), LinkedHashMap::new, Collectors.mapping(ma -> AttachFileVo.builder()
                                        .url(ma.getUrl())
                                        .subheading(ma.getSubheading())
                                        .build(), Collectors.toList())
                                ))));


        modelAndView.addObject("videoUrls", videoUrls);
        modelAndView.addObject("pdfUrls", pdfUrls);
        modelAndView.addObject("files", contentFiles);
//        modelAndView.addObject("videoAttachList", groupedVideoAttach);
        modelAndView.addObject("audioAttachList", groupedAudioAttach);
    }
}
