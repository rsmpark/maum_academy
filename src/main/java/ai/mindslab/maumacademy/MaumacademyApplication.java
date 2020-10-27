package ai.mindslab.maumacademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("ai.mindslab")
@SpringBootApplication
public class MaumacademyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaumacademyApplication.class, args);
    }

}
