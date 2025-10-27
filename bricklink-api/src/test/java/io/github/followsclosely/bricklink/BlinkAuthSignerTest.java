package io.github.followsclosely.bricklink;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BlinkAuthSignerTest {
    String API_BASE_URL = "https://api.bricklink.com/api/store/v1";

    @Test
    void newSigner() throws Exception {
        BlinkAuthSigner blinkAuthSigner = new BlinkAuthSigner(
            "consumerKeyExample1234567890",
            "customerSecretExample1234567890",
            "tokenValueExample1234567890",
            "tokenSecretExample"
        );

        String path = "/item_mapping/6284676";
        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
            .verb(BlinkAuthSigner.Method.GET)
            .uri(API_BASE_URL + path)
            .parameters(null);

        String authorizationHeader = signatureBuilder.buildAuthorizationHeader();

        System.out.println(authorizationHeader);
        assertNotNull(authorizationHeader);
        assertTrue(authorizationHeader.startsWith("OAuth "), "Header must start with 'OAuth '");

        // Parse OAuth header into a map for readable assertions
        java.util.Map<String, String> fieldMap = parseOAuthHeader(authorizationHeader);

        // --- Static fields ---
        assertEquals("consumerKeyExample1234567890", fieldMap.get("oauth_consumer_key"), "consumer key");
        assertEquals("tokenValueExample1234567890", fieldMap.get("oauth_token"), "token value");
        assertEquals("HMAC-SHA1", fieldMap.get("oauth_signature_method"), "signature method");
        assertEquals("1.0", fieldMap.get("oauth_version"), "version");

        // --- Randomized fields ---
        assertNotNull(fieldMap.get("oauth_nonce"), "nonce should not be null");
        assertFalse(fieldMap.get("oauth_nonce").isEmpty(), "nonce should not be empty");
        assertTrue(fieldMap.get("oauth_signature").matches("[\\w%]+"), "signature format");
        assertTrue(fieldMap.get("oauth_timestamp").matches("\\d+"), "timestamp format");
    }

    /**
     * Helper to parse an OAuth header string into a map of key-value pairs.
     */
    private static java.util.Map<String, String> parseOAuthHeader(String header) {
        java.util.Map<String, String> map = new java.util.HashMap<>();
        String prefix = "OAuth ";
        String headerFields = header.startsWith(prefix) ? header.substring(prefix.length()) : header;
        String[] pairs = headerFields.split(", ");
        for (String pair : pairs) {
            String[] kv = pair.split("=", 2);
            if (kv.length == 2) {
                String key = kv[0];
                String value = kv[1].replaceAll("^\"|\"$", ""); // Remove surrounding quotes
                map.put(key, value);
            }
        }
        return map;
    }
}