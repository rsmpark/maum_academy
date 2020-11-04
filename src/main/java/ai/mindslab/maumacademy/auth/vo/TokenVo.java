package ai.mindslab.maumacademy.auth.vo;

import lombok.Data;

@Data
public class TokenVo {
    private String email;
    private String name;
    private String phone;

    private String access_token;
    private String access_expire_time;

    private String refresh_token;                    // AccessToken을 재발급(재로그인) 받기위한 토큰
    private String refresh_expire_time;

    private int db_diff_time;
}
