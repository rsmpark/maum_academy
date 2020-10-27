//package ai.mindslab.maumacademy.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.resource.VersionResourceResolver;
//
//@Configuration
//@EnableWebMvc
//public class WebConfig implements WebMvcConfigurer {
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
//}
