package io.github.followsclosely.bricklink;

import io.github.followsclosely.toolbox.web.cache.DiskCachingConfiguration;
import io.github.followsclosely.toolbox.web.limiter.ApiRateLimiterConfiguration;
import io.github.followsclosely.toolbox.web.limiter.DailyApiLimitConfiguration;
import lombok.Getter;
import lombok.Setter;
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

    private DiskCachingConfiguration caching = new DiskCachingConfiguration();
    private ApiRateLimiterConfiguration apiLimits = new ApiRateLimiterConfiguration();
    private DailyApiLimitConfiguration dailyLimit = new DailyApiLimitConfiguration();
}
