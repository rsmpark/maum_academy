package ai.mindslab.maumacademy.common.controller;

import ai.mindslab.maumacademy.common.service.MailSender;
import ai.mindslab.maumacademy.common.vo.SupportForm;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/support")
public class SupportController {
    @Autowired
    private MailSender mailSender;

    /** 사용자가 contact 메일 보내기 - 2020. 01. 10 - LYJ */
    @RequestMapping(value = "/sendContactMail", method={RequestMethod.POST})
    @ResponseBody
    public String sendContactMail(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "fromaddr") String fromaddr,
                                  @RequestParam(value = "toaddr") String toaddr,
                                  @RequestParam(value = "subject") String subject,
                                  @RequestParam(value = "message") String msg) throws Exception {

        System.out.println("sendContactMail ==> " + fromaddr);

        String rtnValue =   mailSender.sendPostWithForm(fromaddr, toaddr, subject, msg);

        return rtnValue;
    }

}
