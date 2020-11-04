package ai.mindslab.maumacademy.auth.service;

import ai.mindslab.maumacademy.auth.repository.AuthRepository;
import ai.mindslab.maumacademy.auth.repository.UserRepository;
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
    UserRepository user_repository;

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

            //TODO: user가 null. user database를 maum.ai에서 data를 가져오는게 맞는것으로 생각.
            UserVo userVo = user_repository.getUserByEmail(token.getEmail());
            return userVo;
        }
        else return null;
    }

    public String makeDeleteTokenUrl(UserVo user){

        TokenVo token = auth_repository.getAuth(user.getEmail());

        String url = hqUrl + hqCleanToken;
        String params = "?client_id=" + CLIENT_ID + "&access_token=" + token.getAccess_token();

        String resultUrl = url + params;

        return resultUrl;
    }


    public void logoutByEmail(UserVo user) {
        try {
            TokenVo token = auth_repository.getAuth(user.getEmail());
            if(token == null) return;
            auth_repository.deleteAuth(user.getEmail());

            sso.cleanTokens(CLIENT_ID, token.getAccess_token());
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
