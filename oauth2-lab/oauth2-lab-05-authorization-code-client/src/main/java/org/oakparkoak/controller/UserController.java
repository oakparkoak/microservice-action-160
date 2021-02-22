package org.oakparkoak.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.oakparkoak.config.oauth2.AuthorizationConfiguration;
import org.oakparkoak.config.oauth2.Oauth2Token;
import org.oakparkoak.config.security.ClientUserDetails;
import org.oakparkoak.model.ClientUser;
import org.oakparkoak.model.ServerUser;
import org.oakparkoak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 * @package: org.oakparkoak.api
 * @author: Captain
 * @time: 2/3/2021 6:25 PM
 */
@Slf4j
@Controller
public class UserController {
    @Autowired
    AuthorizationConfiguration authorization;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserRepository repo;

    @GetMapping("/index")
    public ModelAndView getUser() throws UnsupportedEncodingException {
        ClientUserDetails principal = (ClientUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ClientUser clientUser = principal.getUser();
        if (clientUser.getAccessToken() == null) {
            return new ModelAndView("redirect:" + authorization.getAuthorizationEndpoint());
        }
        return getUserFromResourceServer(clientUser.getAccessToken());
    }

    @GetMapping("/callback")
    public ModelAndView callback(String code) {
        ClientUserDetails principal = (ClientUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ClientUser clientUser = principal.getUser();

        Oauth2Token token = authorization.getToken(code);
        clientUser.setAccessToken(token.getAccessToken());
        clientUser.setAccessTokenValidity(new Date(System.currentTimeMillis() + Long.parseLong(token.getExpiresIn())));
        repo.save(clientUser);

        return new ModelAndView("redirect:/index");
    }

    private ModelAndView getUserFromResourceServer(String token) {
        ModelAndView modelAndView = new ModelAndView("index");

        String endpoint = "http://localhost:8080/api/u";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", "Bearer " + token);

        RequestEntity<Object> request = new RequestEntity<>(headers, HttpMethod.GET, URI.create(endpoint));
        ResponseEntity<ServerUser> response = restTemplate.exchange(request, ServerUser.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            modelAndView.addObject("user", response.getBody());
        } else {
            throw new RuntimeException("Error, can't retrieve user profile");
        }
        return modelAndView;
    }
}
