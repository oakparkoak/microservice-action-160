package org.oakparkoak.config.oauth2;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @package: org.oakparkoak.config.oauth2
 * @author: Captain
 * @time: 2/9/2021 5:39 PM
 */
@Configuration
public class AuthorizationCodeTokenService {
    @Autowired
    private AuthorizationCodeConfiguration configuration;

    private String getUrl(String endpoint, Map<String, String> param) {

        List<String> params2 = new ArrayList<>(param.size());

        param.forEach((k, v) -> params2.add(k + "=" + v));

        return endpoint + "?" + params2.stream().reduce("", (acc, a) -> acc + "&" + a);
    }

    public String getAuthorizationEndpoint() throws UnsupportedEncodingException {
        String endpoint = "http://localhost:8080/oauth/authorize";

        Map<String, String> authParameters = new HashMap<>(6);
        authParameters.put("client_id", "client5");
        authParameters.put("response_type", "code");
        authParameters.put("redirect_uri", URLEncoder.encode("http://localhost:8081/callback", "UTF-8"));
        authParameters.put("scope", "read");

        return getUrl(endpoint, authParameters);
    }

    public Oauth2Token getToken(String authorizationCode) {
        RestTemplate restTemplate = new RestTemplate();
        String authBase64 = configuration.encodeCredentials("client5", "555");

        RequestEntity<MultiValueMap<String, String>> request = new RequestEntity<>(
                configuration.buildRequestBody(authorizationCode),
                configuration.buildRequestHeader(authBase64),
                HttpMethod.POST,
                URI.create("http://localhost:8080/oauth/token"));

        ResponseEntity<Oauth2Token> response = restTemplate.exchange(request, Oauth2Token.class);

        if (response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }

        throw  new RuntimeException("Error trying to retrieve access token!");
    }
}
