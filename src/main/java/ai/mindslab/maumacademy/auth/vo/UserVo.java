package ai.mindslab.maumacademy.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class UserVo implements Serializable {
    private int userNo;
    private String name;
    private String email;
    private String phone;
}
