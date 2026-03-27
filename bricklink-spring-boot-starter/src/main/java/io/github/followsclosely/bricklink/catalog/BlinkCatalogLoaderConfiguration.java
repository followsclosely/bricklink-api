package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.BlinkConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.File;

@Slf4j
@Configuration
@ConditionalOnClass(AbstractCatalogLoader.class)
public class BlinkCatalogLoaderConfiguration {

    private final File rootDirectory;

    @Autowired
    public BlinkCatalogLoaderConfiguration(BlinkConfiguration configuration) {
        log.info("Initializing Bricklink Catalog Loader Configuration");
        if (configuration.getCatalog() == null) {
            this.rootDirectory = new File(".");
            log.warn("Bricklink Set Catalog Loader root directory is not set in configuration, defaulting to {}.", this.rootDirectory.getAbsolutePath());
        } else {
            File rootDirectory = new File(configuration.getCatalog());
            if (rootDirectory.exists()) {
                this.rootDirectory = rootDirectory;
                log.info("Setting Bricklink Set Catalog Loader root directory to existing path: {}", rootDirectory.getAbsolutePath());
            } else {
                this.rootDirectory = new File(".");
                log.warn("Bricklink Set Catalog Loader root directory does not exist: {}, defaulting to {}.", rootDirectory.getAbsolutePath(), this.rootDirectory.getAbsolutePath());
            }
        }
    }

    @Bean
    @Lazy
    @ConditionalOnMissingBean(BlinkSetCatalogLoader.class)
    public BlinkSetCatalogLoader blinkSetCatalogLoader() {
        BlinkSetCatalogLoader loader = new BlinkSetCatalogLoader();
        loader.setRootDirectory(rootDirectory.getAbsolutePath());
        log.info("Created BlinkSetCatalogLoader bean: {}", loader);
        return loader;
    }

    @Bean
    @Lazy
    @ConditionalOnMissingBean(BlinkCategoryCatalogLoader.class)
    public BlinkCategoryCatalogLoader blinkCategoryCatalogLoader() {
        BlinkCategoryCatalogLoader loader = new BlinkCategoryCatalogLoader();
        loader.setRootDirectory(rootDirectory.getAbsolutePath());
        log.info("Created BlinkCategoryCatalogLoader bean: {}", loader);
        return loader;
    }

    @Bean
    @Lazy
    @ConditionalOnMissingBean(BlinkMinifigureCatalogLoader.class)
    public BlinkMinifigureCatalogLoader blinkMinifigureCatalogLoader() {
        BlinkMinifigureCatalogLoader loader = new BlinkMinifigureCatalogLoader();
        loader.setRootDirectory(rootDirectory.getAbsolutePath());
        log.info("Created BlinkMinifigureCatalogLoader bean: {}", loader);
        return loader;
    }

    @Bean
    @Lazy
    @ConditionalOnMissingBean(BlinkPartCatalogLoader.class)
    public BlinkPartCatalogLoader blinkPartCatalogLoader() {
        BlinkPartCatalogLoader loader = new BlinkPartCatalogLoader();
        loader.setRootDirectory(rootDirectory.getAbsolutePath());
        log.info("Created BlinkPartCatalogLoader bean: {}", loader);
        return loader;
    }

    @Bean
    @Lazy
    @ConditionalOnMissingBean(BlinkBookCatalogLoader.class)
    public BlinkBookCatalogLoader blinkBookCatalogLoader() {
        BlinkBookCatalogLoader loader = new BlinkBookCatalogLoader();
        loader.setRootDirectory(rootDirectory.getAbsolutePath());
        log.info("Created BlinkBookCatalogLoader bean: {}", loader);
        return loader;
    }

    @Bean
    @Lazy
    @ConditionalOnMissingBean(BlinkGearCatalogLoader.class)
    public BlinkGearCatalogLoader blinkGearCatalogLoader() {
        BlinkGearCatalogLoader loader = new BlinkGearCatalogLoader();
        loader.setRootDirectory(rootDirectory.getAbsolutePath());
        log.info("Created BlinkGearCatalogLoader bean: {}", loader);
        return loader;
    }
}