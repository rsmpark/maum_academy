package ai.mindslab.maumacademy.auth.service;

import ai.mindslab.maumacademy.auth.domain.Auth;
import ai.mindslab.maumacademy.auth.repository.AuthRepository;
import ai.mindslab.maumacademy.auth.vo.TokenVo;
import ai.mindslab.maumacademy.auth.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class AuthService {
    @Autowired
    AuthRepository auth_repository;

    @Autowired
    SSOService sso;

    @Value("${sso.client-id}")
    String CLIENT_ID;

    @Value("${url.hq}")
    String hqUrl;

    @Value("${hq.logoutPath}")
    String hqCleanToken;

    public UserVo getAuthToken(String code, String state, String requestUri) {

        TokenVo token = sso.publishTokens(code, requestUri);
        if(token != null) {
            auth_repository.deleteAuth(token.getEmail());
            auth_repository.insertAuth(token);

            UserVo userVo = UserVo.builder().email(token.getEmail()).name(token.getName()).build();
            // Token --> UserVo DB save
            return userVo;
        }
        else return null;
    }


    public String makeDeleteTokenUrl(UserVo user){

        Auth auth = auth_repository.getAuth(user.getEmail());

        TokenVo token = TokenVo.builder()
                .access_token(auth.getAccess_token())
                .refresh_token(auth.getRefresh_token())
                .access_expire_time(auth.getAccess_expire_time())
                .refresh_expire_time(auth.getRefresh_expire_time())
                .email(auth.getEmail())
                .build();

        String url = hqUrl + hqCleanToken;
        String params = "?client_id=" + CLIENT_ID + "&access_token=" + token.getAccess_token();

        String resultUrl = url + params;

        return resultUrl;
    }


    public void logoutByEmail(UserVo user) {
        try {
            Auth auth = auth_repository.getAuth(user.getEmail());

            TokenVo token = TokenVo.builder()
                    .access_token(auth.getAccess_token())
                    .refresh_token(auth.getRefresh_token())
                    .access_expire_time(auth.getAccess_expire_time())
                    .refresh_expire_time(auth.getRefresh_expire_time())
                    .email(auth.getEmail())
                    .build();

            if(auth == null) return;
            auth_repository.deleteAuth(user.getEmail());

            sso.cleanTokens(CLIENT_ID, token.getAccess_token());
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
