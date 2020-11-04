package ai.mindslab.maumacademy.auth.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
    private int userNo;
    private String name;
    private String email;
    private String phone;
}
