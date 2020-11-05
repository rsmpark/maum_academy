package ai.mindslab.maumacademy.config;

import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import java.util.Collections;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/css/**").addResourceLocations("/css/").resourceChain(false).addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
//        registry.addResourceHandler("/js/**").addResourceLocations("/js/").resourceChain(false).addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
//        registry.addResourceHandler("/font/**").addResourceLocations("/font/");
//        registry.addResourceHandler("/webfonts/**").addResourceLocations("/webfonts/");
//        registry.addResourceHandler("/audio/**").addResourceLocations("/audio/");
//        registry.addResourceHandler("/image/**").addResourceLocations("/image/");
//        registry.addResourceHandler("/favicon.ico").addResourceLocations("/");
//    }

    @Bean
    public ServletContextInitializer clearJsession() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
                SessionCookieConfig sessionCookieConfig=servletContext.getSessionCookieConfig();
                sessionCookieConfig.setHttpOnly(true);
            }
        };
    }
}
