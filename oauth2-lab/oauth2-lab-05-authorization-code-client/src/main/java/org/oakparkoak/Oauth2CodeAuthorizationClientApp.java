package org.oakparkoak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @package: org.oakparkoak
 * @author: Captain
 * @time: 2/3/2021 6:26 PM
 */
@SpringBootApplication
public class Oauth2CodeAuthorizationClientApp {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2CodeAuthorizationClientApp.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
