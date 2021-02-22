package org.oakparkoak.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @package: org.oakparkoak.config
 * @author: Captain
 * @time: 2/4/2021 3:44 PM
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client5")
                .secret("555")
                .redirectUris("http://localhost:8081/callback")
                .authorizedGrantTypes("authorization_code")
                .accessTokenValiditySeconds(600)
                .scopes("read");

    }
}
