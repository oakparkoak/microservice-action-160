package org.oakparkoak.config.oauth2;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @package: org.oakparkoak.config.oauth2
 * @author: Captain
 * @time: 2/9/2021 5:39 PM
 */
@Configuration
public class AuthorizationConfiguration {
    @Autowired
    RestTemplate restTemplate;

    public String getAuthorizationEndpoint() throws UnsupportedEncodingException {
        String endpoint = "http://localhost:8080/oauth/authorize";

        Map<String, String> kv = new HashMap<>(6);
        kv.put("client_id", "client5");
        kv.put("response_type", "code");
        kv.put("redirect_uri", URLEncoder.encode("http://localhost:8081/callback", "UTF-8"));
        kv.put("scope", "read");

        return buildEndpoint(endpoint, kv);
    }

    public Oauth2Token getToken(String authorizationCode) {
        String credentials = encodeCredentials();
        RequestEntity<MultiValueMap<String, String>> request = new RequestEntity<>(
                buildRequestBody(authorizationCode),
                buildRequestHeader(credentials),
                HttpMethod.POST,
                URI.create("http://localhost:8080/oauth/token"));

        ResponseEntity<Oauth2Token> response = restTemplate.exchange(request, Oauth2Token.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new RuntimeException("Error trying to retrieve access token!");
    }

    private String buildEndpoint(String endpoint, Map<String, String> kv) {
        List<String> params = new ArrayList<>(kv.size());
        kv.forEach((k, v) -> params.add(k + "=" + v));

        return endpoint + "?" + params.stream().reduce("", (acc, a) -> acc + "&" + a);
    }

    private String encodeCredentials() {
        return new String(Base64.getEncoder().encode(("client5" + ":" + "555").getBytes()));
    }

    private MultiValueMap<String, String> buildRequestBody(String authorizationCode) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "authorization_code");
        form.add("scope", "read");
        form.add("code", authorizationCode);
        form.add("redirect_uri", "http://localhost:8081/callback");

        return form;
    }

    private HttpHeaders buildRequestHeader(String credentials) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Basic " + credentials);

        return headers;
    }
}
