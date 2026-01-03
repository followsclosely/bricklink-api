package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.BlinkConfiguration;
import io.github.followsclosely.bricklink.BlinkItemClient;
import io.github.followsclosely.bricklink.dto.BlinkItem;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import io.github.followsclosely.bricklink.oauth.BlinkAuthSigner;
import io.github.followsclosely.toolbox.PathBuilder;
import io.github.followsclosely.toolbox.web.cache.DiskCachingClientHttpRequestInterceptor;
import io.github.followsclosely.toolbox.web.cache.DiskCachingHint;
import io.github.followsclosely.toolbox.web.limiter.ApiRateLimiterClientHttpRequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Configuration
@EnableConfigurationProperties(BlinkConfiguration.class)
public class BlinkRestClientConfiguration {

    @Bean
    @Lazy
    @ConditionalOnMissingBean(value = BlinkAuthSigner.class, name = "blinkAuthSigner")
    BlinkAuthSigner blinkAuthSigner(BlinkConfiguration c) {
        return new BlinkAuthSigner(
                c.getConsumerKey(), c.getConsumerSecret(), c.getTokenValue(), c.getTokenSecret()
        );
    }

    @Bean(name = "blinkRestClient")
    @Lazy
    @ConditionalOnMissingBean(value = RestClient.class, name = "blinkRestClient")
    RestClient blinkRestClient(BlinkConfiguration configuration) {

        RestClient.Builder builder = RestClient.builder()
                .baseUrl(configuration.getBaseUrl())
                .defaultHeaders(headers -> {
                    headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
                });

        if (log.isDebugEnabled()) {
            builder.defaultStatusHandler(response -> {
                log.debug("HttpResponse {} : {}", response.getStatusCode(), response.getStatusText());
                response.getHeaders().forEach((key, values) -> log.debug("Header: {}={}", key, values));
                return response.getStatusCode().is2xxSuccessful();
            });
        }

        if (configuration.getApiLimits() != null && configuration.getApiLimits().isEnabled()) {
            if (configuration.getCaching() != null && configuration.getCaching().isEnabled()) {
                builder.requestInterceptor(new DiskCachingClientHttpRequestInterceptor(
                        configuration.getCaching(), configuration.getApiLimits()));
            } else {
                builder.requestInterceptor(new ApiRateLimiterClientHttpRequestInterceptor(configuration.getApiLimits()));
            }
        } else if (configuration.getCaching() != null && configuration.getCaching().isEnabled()) {
            builder.requestInterceptor(
                    new DiskCachingClientHttpRequestInterceptor(configuration.getCaching())
            );
        }

        return builder.build();
    }

    @Bean
    @Lazy
    @ConditionalOnClass(BlinkCategoryRestClient.class)
    @ConditionalOnMissingBean(BlinkCategoryRestClient.class)
    BlinkCategoryRestClient blinkCategoryRestClient(
            @Qualifier("blinkRestClient") RestClient blinkRestClient,
            BlinkAuthSigner blinkAuthSigner
    ) {
        log.info("Creating new BlinkCategoryRestClient({},{})", blinkRestClient, blinkAuthSigner);
        return new BlinkCategoryRestClient(blinkAuthSigner, blinkRestClient);
    }

    @Bean
    @Lazy
    @ConditionalOnClass(BlinkColorRestClient.class)
    @ConditionalOnMissingBean(BlinkColorRestClient.class)
    BlinkColorRestClient blinkColorRestClient(
            @Qualifier("blinkRestClient") RestClient blinkRestClient,
            BlinkAuthSigner blinkAuthSigner
    ) {
        log.info("Creating new BlinkColorRestClient({},{})", blinkRestClient, blinkAuthSigner);
        return new BlinkColorRestClient(blinkAuthSigner, blinkRestClient);
    }

    @Bean
    @Lazy
    @ConditionalOnClass(BlinkItemClient.class)
    @ConditionalOnMissingBean(BlinkItemClient.class)
    BlinkItemClient blinkItemRestClient(
            @Qualifier("blinkRestClient") RestClient blinkRestClient,
            BlinkAuthSigner blinkAuthSigner,
            BlinkConfiguration configuration
    ) {
        log.info("Creating new BlinkItemRestClient({},{})", blinkRestClient, blinkAuthSigner);

        if (configuration.getCaching() != null && configuration.getCaching().isEnabled()) {
            return new BlinkItemRestClient(blinkAuthSigner, blinkRestClient) {
                public BlinkResponse<List<BlinkItem.SubsetEntry>> getItemSubsets(BlinkItem.Type type, String number, ItemSubsetsQuery query) {
                    switch(type) {
                        case MINIFIG:
                            DiskCachingHint.set(new PathBuilder()
                                    .add("minifigs")
                                    .explodeOnGroups(number, "^(.*[a-zA-Z]+)\\d+.*$")
                                    .add(number+"-bricklink-inventory")
                                    .toArray());
                            break;
                        case SET:
                            DiskCachingHint.set(new PathBuilder()
                                    .add("sets")
                                    .explode(number)
                                    .add(number+"-bricklink-inventory")
                                    .toArray());
                            break;
                        default:
                            DiskCachingHint.set(new PathBuilder()
                                    .add("subsets")
                                    .explodeOnGroups(number, "^(.*[a-zA-Z]+)\\d+.*$")
                                    .add(number+"-bricklink-inventory")
                                    .toArray());
                    }

                    try {
                        return super.getItemSubsets(type, number, query);
                    } finally {
                        DiskCachingHint.clear();
                    }
                }
            };
        } else {
            return new BlinkItemRestClient(blinkAuthSigner, blinkRestClient);
        }
    }

}
