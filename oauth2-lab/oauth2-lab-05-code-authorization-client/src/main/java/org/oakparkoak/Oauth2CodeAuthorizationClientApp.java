package org.oakparkoak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

    //@Override
    //public void onStartup(ServletContext servletContext) {
    //    servletContext.getSessionCookieConfig().setName("client-session");
    //}
}
