package ai.mindslab.maumacademy.contents.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MediaAttachVo {
    private int pageNum;
    private String pageTitle;
    private List<AttachFileVo> attachFiles;
}
