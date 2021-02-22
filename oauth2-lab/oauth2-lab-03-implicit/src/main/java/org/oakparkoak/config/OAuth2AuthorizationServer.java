package org.oakparkoak.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @package: org.oakparkoak.config
 * @author: Captain
 * @time: 2/9/2021 3:44 PM
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client3")
                .secret("333")
                .redirectUris("http://localhost:8080/callback")
                .authorizedGrantTypes("implicit")
                .accessTokenValiditySeconds(120)
                .scopes("read", "write");
    }
}
