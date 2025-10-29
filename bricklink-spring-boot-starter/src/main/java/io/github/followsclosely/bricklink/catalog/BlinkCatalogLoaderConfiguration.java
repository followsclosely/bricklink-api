package io.github.followsclosely.bricklink.catalog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Slf4j
@Configuration
@ConditionalOnClass(AbstractCatalogLoader.class)
public class BlinkCatalogLoaderConfiguration {

    @Bean @Lazy
    @ConditionalOnMissingBean(BlinkCatalogLoaderConfiguration.class)
    public BlinkCatalogLoaderConfiguration blinkCatalogLoaderConfiguration() {
        return new BlinkCatalogLoaderConfiguration();
    }
}