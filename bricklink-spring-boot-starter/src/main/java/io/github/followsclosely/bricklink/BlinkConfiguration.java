package io.github.followsclosely.bricklink;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "bricklink", ignoreUnknownFields = false)
public class BlinkConfiguration {

    private String baseUrl = "https://rebrickable.com/api/v3/lego/";

    private String consumerKey = null;
    private String consumerSecret = null;
    private String tokenValue = null;
    private String tokenSecret = null;
}
