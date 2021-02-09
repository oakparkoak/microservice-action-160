package org.oakparkoak.controller;

import lombok.extern.slf4j.Slf4j;
import org.oakparkoak.model.UserB;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: org.oakparkoak.api
 * @author: Captain
 * @time: 2/9/2021 6:25 PM
 */
@Slf4j
@RestController
public class UserControllerB {
    @GetMapping("/api/u")
    public ResponseEntity<UserB> getUser() {
        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Principal: {}", principal);

        UserB user = new UserB();
        user.setUsername(principal.getUsername());
        user.setEmail("captain@gmail.com");
        return ResponseEntity.ok(user);
    }
}
