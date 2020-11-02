package ai.mindslab.maumacademy.contents.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachFileVo {
    private String url;
    private String subheading;
}
