package org.oakparkoak.config.oauth2;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * @package: org.oakparkoak.config.oauth2
 * @author: Captain
 * @time: 2/9/2021 5:14 PM
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Oauth2Token {
    private String accessToken;

    private String tokenType;

    private String expiresIn;

    private String refreshToken;
}
