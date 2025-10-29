package io.github.followsclosely.bricklink;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public class BlinkApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final List<String> REQUIRED_KEYS = List.of(
            "bricklink.consumerKey", "bricklink.consumerSecret", "bricklink.tokenValue", "bricklink.tokenSecret"
    );

    /**
     * Obfuscate a key by replacing all but the first and last few characters with asterisks.
     *
     * @param key           the key to obfuscate
     * @param previewLength the number of characters to show at the start and end of the key
     * @return the obfuscated key
     */
    private static String obfuscateKey(String key, int previewLength) {
        if (key == null || key.length() <= previewLength) {
            return key == null ? null : "*".repeat(key.length());
        }
        int maskLength = key.length() - previewLength;
        return key.substring(0, previewLength / 2) + "*".repeat(maskLength) + key.substring(key.length() - previewLength / 2);
    }

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {

        for( String key : REQUIRED_KEYS) {
            String keyValue = event.getEnvironment().getProperty(key);

            if (!StringUtils.hasLength(keyValue)) {
                throw new RuntimeException("Missing Resources " + String.join(",", REQUIRED_KEYS) + " are required to initialize the RestClient bean.");
            } else {
                log.info("Found Resource: \"{}\" is set to: {}", key, obfuscateKey(keyValue, 10));
            }
        }
    }
}
