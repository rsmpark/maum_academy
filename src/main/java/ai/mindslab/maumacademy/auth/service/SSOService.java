package ai.mindslab.maumacademy.auth.service;

import ai.mindslab.maumacademy.auth.vo.TokenVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SSOService {

    @Value("${url.hq}")
    String SSO_SERVER_HQ;

    public TokenVo publishTokens(String code, String redirectUri) {
        try {
            String url = SSO_SERVER_HQ + "/hq/oauth/token";
            HttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);


            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addPart("grant_type", new StringBody("authorization_code", ContentType.TEXT_PLAIN.withCharset("UTF-8")));
            builder.addPart("code", new StringBody(code, ContentType.TEXT_PLAIN.withCharset("UTF-8")));
            builder.addPart("redirect_uri",new StringBody(redirectUri, ContentType.TEXT_PLAIN.withCharset("UTF-8")));
            HttpEntity entity = builder.build();
            post.setEntity(entity);

            HttpResponse response = client.execute(post);

            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == 200) {
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                log.info("response = {}", result);

                ObjectMapper mapper = new ObjectMapper();
                TokenVo token = mapper.readValue(result, TokenVo.class);

                return token;
            }
            else {
                log.error("Response Code : {}", responseCode);
            }
        } catch(Exception e) {
            log.error(e.toString());
        }

        return null;
    }

    public TokenVo republishTokens(String refreshToken) {
        try {
            String url = SSO_SERVER_HQ + "/hq/oauth/token";
            HttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);


            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addPart("grant_type", new StringBody("refresh_token", ContentType.TEXT_PLAIN.withCharset("UTF-8")));
            builder.addPart("refresh_token", new StringBody(refreshToken, ContentType.TEXT_PLAIN.withCharset("UTF-8")));
            HttpEntity entity = builder.build();
            post.setEntity(entity);

            HttpResponse response = client.execute(post);

            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == 200) {
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                log.info("response = {}", result);

                ObjectMapper mapper = new ObjectMapper();
                TokenVo token = mapper.readValue(result, TokenVo.class);

                return token;
            }
            else {
                log.error("Response Code : {}", responseCode);
            }
        } catch(Exception e) {
            log.error(e.toString());
        }

        return null;
    }

    public void cleanTokens(String clientId, String accessToken) {
        try {
            String url = SSO_SERVER_HQ + "/hq/oauth/token";
            HttpClient client = HttpClients.createDefault();
            //MyHttpDelete deleteMethod = new MyHttpDelete(url);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addPart("client_id", new StringBody(clientId, ContentType.TEXT_PLAIN.withCharset("UTF-8")));
            builder.addPart("access_token", new StringBody(accessToken, ContentType.TEXT_PLAIN.withCharset("UTF-8")));
            HttpEntity entity = builder.build();
            //deleteMethod.setEntity(entity);

            //HttpResponse response = client.execute(deleteMethod);

//            int responseCode = response.getStatusLine().getStatusCode();
//            if (responseCode == 200) {
//                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
//                log.info("response = {}", result);
//            }
//            else {
//                log.error("Response Code : {}", responseCode);
//            }
        } catch(Exception e) {
            log.error(e.toString());
        }
    }

}
