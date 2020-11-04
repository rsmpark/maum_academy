package ai.mindslab.maumacademy.contents.vo;

import lombok.*;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachPageVo{
    private int pageNum;
    private String pageTitle;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AttachPageVo)) {
            return false;
        }

        AttachPageVo that = (AttachPageVo) obj;

        return Objects.equals(this.pageTitle, that.pageTitle) && Objects.equals(this.pageNum, that.pageNum) ;
    }

    @Override public int hashCode() {
        return Objects.hash(pageTitle, pageNum);
    }

}
