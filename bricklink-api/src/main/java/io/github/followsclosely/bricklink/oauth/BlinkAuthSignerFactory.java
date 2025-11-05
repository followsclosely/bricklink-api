package io.github.followsclosely.bricklink.oauth;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Factory class to create BlinkAuthSigner instances from properties files.
 * <p>
 * This is designed to be used for testing or simple applications where
 * configuration is stored in a properties file within the classpath.
 * <p>
 * Warning: This is not suitable for production use where more secure
 * handling of credentials is required!
 */
public class BlinkAuthSignerFactory {

    private static final String DEFAULT_RESOURCE_NAME = "bricklink.properties";
    private static final Map<String, BlinkAuthSigner> SIGNERS = new HashMap<>();

    public static BlinkAuthSigner newInstance() throws IOException {
        return newInstance(DEFAULT_RESOURCE_NAME);
    }

    public static BlinkAuthSigner newInstance(String resourceName) throws IOException {

        if (SIGNERS.containsKey(resourceName)) {
            return SIGNERS.get(resourceName);
        }

        Properties properties = new Properties();
        // The resourceName should be the path within the classpath,
        // e.g., "config/app.properties" or just "app.properties" if at the root.

        // Get the ClassLoader for the current class
        ClassLoader classLoader = BlinkAuthSignerFactory.class.getClassLoader();

        try (InputStream input = classLoader.getResourceAsStream(resourceName)) {
            if (input == null) {
                System.err.println("Sorry, unable to find " + resourceName);
                return null;
            }

            // Load the properties from the InputStream
            properties.load(input);
        }

        BlinkAuthSigner blinkAuthSigner = new BlinkAuthSigner(
                properties.getProperty("consumerKey"),
                properties.getProperty("consumerSecret"),
                properties.getProperty("tokenValue"),
                properties.getProperty("tokenSecret")
        );

        SIGNERS.put(resourceName, blinkAuthSigner);

        return blinkAuthSigner;
    }
}
