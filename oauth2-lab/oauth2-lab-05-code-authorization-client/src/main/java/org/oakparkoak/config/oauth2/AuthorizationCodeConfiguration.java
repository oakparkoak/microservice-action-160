package org.oakparkoak.config.oauth2;

import java.util.Base64;
import java.util.Collections;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @package: org.oakparkoak.config.oauth2
 * @author: Captain
 * @time: 2/9/2021 5:17 PM
 */
@Configuration
public class AuthorizationCodeConfiguration {
    public String encodeCredentials(String username, String password) {
        return new String(Base64.getEncoder().encode((username + ":" + password).getBytes()));
    }

    public MultiValueMap<String, String> buildRequestBody(String authorizationCode) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();

        form.add("grant_type", "authorization_code");
        form.add("scope", "read");
        form.add("code", authorizationCode);
        form.add("redirect_uri", "http://localhost:8081/callback");
        return form;
    }

    public HttpHeaders buildRequestHeader(String token) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Basic " + token);

        return headers;
    }
}
