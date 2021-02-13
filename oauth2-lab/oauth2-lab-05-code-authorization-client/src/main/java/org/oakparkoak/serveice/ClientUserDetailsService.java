package org.oakparkoak.serveice;

import org.oakparkoak.config.security.ClientUserDetails;
import org.oakparkoak.model.ClientUserE;
import org.oakparkoak.repository.UserERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @package: org.oakparkoak.config.security
 * @author: Captain
 * @time: 2/9/2021 5:06 PM
 */
@Service
public class ClientUserDetailsService implements UserDetailsService {
    @Autowired
    private UserERepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientUserE clientUserE = repo
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("invalid username or password"));
        return new ClientUserDetails(clientUserE);
    }
}
