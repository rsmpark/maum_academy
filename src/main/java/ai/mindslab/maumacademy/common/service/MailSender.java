package ai.mindslab.maumacademy.common.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class MailSender {
    private final static Logger logger = LoggerFactory.getLogger(MailSender.class);
    public String sendPostWithForm(String fromAddr, String targetAddr, String subject, String message) throws Exception {
        StringBuffer responseString = new StringBuffer();

        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://maum.ai/support/sendMail");

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//        for( String key : parameter.keySet() ){
//            builder.addPart(key, new StringBody((String)parameter.get(key), ContentType.TEXT_PLAIN.withCharset("UTF-8")));
//        }
        builder.addPart("fromaddr", new StringBody(fromAddr, ContentType.TEXT_PLAIN.withCharset("UTF-8")));
        builder.addPart("toaddr", new StringBody(targetAddr, ContentType.TEXT_PLAIN.withCharset("UTF-8")));
        builder.addPart("subject", new StringBody(subject, ContentType.TEXT_PLAIN.withCharset("UTF-8")));
        builder.addPart("message", new StringBody(message, ContentType.TEXT_PLAIN.withCharset("UTF-8")));

        HttpEntity entity = builder.build();
        post.setEntity(entity);

        HttpResponse response = client.execute(post);

        int responseCode = response.getStatusLine().getStatusCode();
        BufferedReader br;
        if(responseCode == 200) {
            HttpEntity resEntity = response.getEntity();
            br = new BufferedReader(new InputStreamReader(resEntity.getContent(), "UTF-8"));
        } else {
            return null;
        }
        String inputLine;
        while((inputLine = br.readLine()) != null) {
            responseString.append(inputLine);
        }
        br.close();

        return responseString.toString();
    }
}
