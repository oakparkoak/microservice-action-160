package org.oakparkoak.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.sql.Date;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.oakparkoak.config.oauth2.AuthorizationCodeTokenService;
import org.oakparkoak.config.oauth2.Oauth2Token;
import org.oakparkoak.config.security.ClientUserDetails;
import org.oakparkoak.model.ClientUserE;
import org.oakparkoak.model.EntryE;
import org.oakparkoak.model.UserE;
import org.oakparkoak.repository.UserERepository;
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
public class UserControllerE {
    @Autowired
    AuthorizationCodeTokenService tokenService;

    @Autowired
    UserERepository repo;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/mainpage")
    public ModelAndView getUser() throws UnsupportedEncodingException {
        ClientUserDetails principal = (ClientUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ClientUserE clientUser = principal.getClientUser();

        if (clientUser.getAccessToken() == null) {
            return new ModelAndView("redirect:" + tokenService.getAuthorizationEndpoint());
        }

        clientUser.setEntries(Arrays.asList(new EntryE("entry-1"), new EntryE("entry-2")));

        ModelAndView modelAndView = new ModelAndView("mainpage");
        modelAndView.addObject("user", clientUser);

        getUser(modelAndView, clientUser.getAccessToken());
        return modelAndView;
    }

    @GetMapping("/callback")
    public ModelAndView callback(String code, String state) {
        ClientUserDetails principal = (ClientUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ClientUserE clientUser = principal.getClientUser();

        Oauth2Token token = tokenService.getToken(code);
        clientUser.setAccessToken(token.getAccessToken());

        clientUser.setAccessTokenValidity(new Date(System.currentTimeMillis() + Long.parseLong(token.getExpiresIn())));

        repo.save(clientUser);

        return new ModelAndView("redirect:/mainpage");
    }

    private void getUser(ModelAndView modelAndView, String token) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", "Bearer " + token);
        String endpoint = "http://localhost:8080/api/u";

        RequestEntity<Object> request = new RequestEntity<>(headers, HttpMethod.GET, URI.create(endpoint));

        ResponseEntity<UserE> responseEntity = restTemplate.exchange(request, UserE.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            modelAndView.addObject("userInfo", responseEntity.getBody());
        } else {
            throw new RuntimeException("Error, can't retrieve user profile");
        }
    }
}
