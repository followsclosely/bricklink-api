package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.BlinkConfiguration;
import io.github.followsclosely.bricklink.oauth.BlinkAuthSigner;
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

@Slf4j
@Configuration
@EnableConfigurationProperties(BlinkConfiguration.class)
public class BlinkRestClientConfiguration {

    @Bean
    @Lazy
    @ConditionalOnMissingBean(value = BlinkAuthSigner.class, name = "blinkAuthSigner")
    BlinkAuthSigner blinkAuthSigner(BlinkConfiguration configuration) {
        return new BlinkAuthSigner(
                configuration.getConsumerKey(), configuration.getConsumerSecret(),
                configuration.getTokenValue(), configuration.getTokenSecret());
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

        return builder.build();
    }

    @Bean
    @Lazy
    @ConditionalOnClass(BlinkCategoryRestClient.class)
    @ConditionalOnMissingBean(BlinkCategoryRestClient.class)
    BlinkCategoryRestClient blinkCategoryRestClient(@Qualifier("blinkRestClient") RestClient blinkRestClient, BlinkAuthSigner blinkAuthSigner) {
        log.info("Creating new BlinkCategoryRestClient({},{})", blinkRestClient, blinkAuthSigner);
        return new BlinkCategoryRestClient(blinkRestClient, blinkAuthSigner);
    }

    @Bean
    @Lazy
    @ConditionalOnClass(BlinkColorRestClient.class)
    @ConditionalOnMissingBean(BlinkColorRestClient.class)
    BlinkColorRestClient blinkColorRestClient(@Qualifier("blinkRestClient") RestClient blinkRestClient, BlinkAuthSigner blinkAuthSigner) {
        log.info("Creating new BlinkColorRestClient({},{})", blinkRestClient, blinkAuthSigner);
        return new BlinkColorRestClient(blinkRestClient, blinkAuthSigner);
    }

    @Bean
    @Lazy
    @ConditionalOnClass(BlinkItemRestClient.class)
    @ConditionalOnMissingBean(BlinkItemRestClient.class)
    BlinkItemRestClient blinkItemRestClient(@Qualifier("blinkRestClient") RestClient blinkRestClient, BlinkAuthSigner blinkAuthSigner) {
        log.info("Creating new BlinkItemRestClient({},{})", blinkRestClient, blinkAuthSigner);
        return new BlinkItemRestClient(blinkRestClient, blinkAuthSigner);
    }

}
