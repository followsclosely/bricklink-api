package io.github.followsclosely.bricklink;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "bricklink", ignoreUnknownFields = false)
public class BlinkConfiguration {

    private String catalog = null;
    private String baseUrl = "https://rebrickable.com/api/v3/lego/";

    private String consumerKey = null;
    private String consumerSecret = null;
    private String tokenValue = null;
    private String tokenSecret = null;

    private ApiLimits apiLimits;

    @Getter
    @Setter
    public static class ApiLimits {
        private long minWaitMsBetweenCalls = 250;
        private long randomMsAddition = 50;
    }
}
