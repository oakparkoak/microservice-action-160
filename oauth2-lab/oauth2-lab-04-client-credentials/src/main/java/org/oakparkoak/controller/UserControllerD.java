package org.oakparkoak.controller;

import lombok.extern.slf4j.Slf4j;
import org.oakparkoak.model.UserD;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: org.oakparkoak.api
 * @author: Captain
 * @time: 2/9/2021 6:25 PM
 */
@Slf4j
@RestController
public class UserControllerD {
    @GetMapping("/api/u")
    public ResponseEntity<UserD> getUser() {
        String principal = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Principal: {}", principal);

        UserD user = new UserD();
        user.setUsername("duser");
        user.setEmail("captain@gmail.com");
        return ResponseEntity.ok(user);
    }
}
