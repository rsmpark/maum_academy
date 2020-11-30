package ai.mindslab.maumacademy.auth.controller;

import ai.mindslab.maumacademy.auth.service.AuthService;
import ai.mindslab.maumacademy.auth.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Controller
public class AuthController {
    @Value("${url.hq}")
    String SSO_SERVER_HQ;

    @Value("${sso.client-id}")
    String CLIENT_ID;

    @Value("${sso.scope}")
    String SCOPE;

    @Value("${sso.callback}")
    String OAUTH_CALLBACK_URI;

    @Value("${url.maumAcademy}")
    String maumAcademyUrl;

    @Autowired
    private AuthService service;


    @RequestMapping(value="/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {

        log.info("Login process : {}, {}", request, response);

        final String SSO_SERVER_URI = SSO_SERVER_HQ + "/hq/oauth/authorize";
        final String RESP_TYPE = "code";

        String state = UUID.randomUUID().toString();
        return "redirect:" + SSO_SERVER_URI + "?"
                + "response_type=" + RESP_TYPE + "&"
                + "client_id=" + CLIENT_ID + "&"
                + "redirect_uri=" + OAUTH_CALLBACK_URI + "&"
                + "scope=" + SCOPE + "&"
                + "state=" + state;

    }

    @RequestMapping(value = "/auth/callback")
    public String authCallback(HttpServletRequest request, @RequestParam("code") String code, @RequestParam("state") String state) throws Exception {

        String log_msg = "\n:: @ REQUEST\n";
        log_msg += ":: ====================================================================================================\n";
        log_msg += String.format(":: %-20s = %s\n", "uri_path", "/security/oauth/callback");
        log_msg += String.format(":: %-20s = %s\n", "desc", "SSO 콜백");
        log_msg += ":: ----------------------------------------------------------------------------------------------------\n";
        log_msg += String.format(":: %-20s = %s\n", "code", code);
        log_msg += String.format(":: %-20s = %s\n", "state", state);
        log_msg += ":: ====================================================================================================\n";
        log.info(log_msg);

        UserVo user = service.getAuthToken(code, state, OAUTH_CALLBACK_URI);
        if(user != null) {
            request.getSession().setAttribute("user", user);
        }
        else {
            request.getSession().removeAttribute("user");
        }

        return "redirect:/";
    }

    @RequestMapping(value="/logout")
    public void logout(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        UserVo user = (UserVo) session.getAttribute("user");
        session.removeAttribute("user");

        String deleteTokenUrl = service.makeDeleteTokenUrl(user);
        String returnUrl = deleteTokenUrl + "&returnUrl=" + maumAcademyUrl;

        service.logoutByEmail(user);

        request.getSession().invalidate();
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect(returnUrl);
    }
}
