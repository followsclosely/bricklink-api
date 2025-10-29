package io.github.followsclosely.bricklink.oauth;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Utility class to sign requests to the BrickLink API using OAuth 1.0a.
 * <p>
 * This class provides methods for generating OAuth 1.0a signatures and constructing signed requests for the BrickLink API.
 * It supports setting the base API URL, building signed URLs, and generating the required Authorization header for requests.
 * <p>
 * <b>Sample usage:</b>
 * <pre>
 *   BlinkAuthSigner signer = new BlinkAuthSigner(consumerKey, consumerSecret, tokenValue, tokenSecret);
 *   BlinkAuthSigner.SignatureBuilder signatureBuilder = signer.signatureBuilder()
 *     .verb(BlinkAuthSigner.Method.GET)
 *     .uri("colors/" + id);
 *
 *   String url = signatureBuilder.buildUrl();
 *   String authHeader = signatureBuilder.buildAuthorizationHeader();
 *   // Use url and authHeader in your HTTP request
 * </pre>
 * <p>
 * See: <a href="https://oauth.net/core/1.0a/">OAuth Core 1.0 Revision A</a>
 * <p>
 */
public class BlinkAuthSigner {

    public static final String VERSION_VALUE = "1.0";
    public static final String HEADER = "Authorization";

    private static final String HEADER_VALUES_PREFIX = "OAuth ";
    private static final Charset CHARSET = StandardCharsets.UTF_8;

    private static final String PARAM_PREFIX = "oauth_";
    private static final String TIMESTAMP = PARAM_PREFIX + "timestamp";
    private static final String SIGN_METHOD = PARAM_PREFIX + "signature_method";
    private static final String SIGNATURE = PARAM_PREFIX + "signature";
    private static final String CONSUMER_SECRET = PARAM_PREFIX + "consumer_secret";
    private static final String CONSUMER_KEY = PARAM_PREFIX + "consumer_key";
    private static final String CALLBACK = PARAM_PREFIX + "callback";
    private static final String VERSION = PARAM_PREFIX + "version";
    private static final String NONCE = PARAM_PREFIX + "nonce";
    private static final String TOKEN = PARAM_PREFIX + "token";
    private static final String TOKEN_SECRET = PARAM_PREFIX + "token_secret";
    private static final String VERIFIER = PARAM_PREFIX + "verifier";

    private static final String OUT_OF_BAND = "oob";
    private static final String SCOPE = "scope";

    private static final String HMAC_SHA1 = "HmacSHA1";
    private static final String SIGN_METHOD_VALUE = "HMAC-SHA1";


    private final String consumerKey;
    private final String consumerSecret;
    private final String tokenValue;
    private final String tokenSecret;

    private final Random random = new Random();

    private String baseUrl = "https://api.bricklink.com/api/store/v1/";

    /**
     * Constructor for BlinkAuthSigner.
     *
     * @param consumerKey    The OAuth consumer key.
     * @param consumerSecret The OAuth consumer secret.
     * @param tokenValue     The OAuth token value.
     * @param tokenSecret    The OAuth token secret.
     */
    public BlinkAuthSigner(String consumerKey, String consumerSecret, String tokenValue, String tokenSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.tokenValue = tokenValue;
        this.tokenSecret = tokenSecret;
    }

    private static String oAuthEncode(String plain) {
        return URLEncoder.encode(plain, CHARSET)
                .replaceAll(Pattern.quote("*"), "%2A")
                .replaceAll(Pattern.quote("+"), "%20")
                .replaceAll(Pattern.quote("%7E"), "~");
    }

    /**
     * Set the base URL for the API endpoints.
     * Note: This method ensures that the base URL ends with a trailing slash.
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        if (!baseUrl.endsWith("/")) {
            this.baseUrl += "/";
        }
    }

    /**
     * Signs the given string using HMAC-SHA1 with the provided key.
     *
     * @param toSign    The string to sign.
     * @param keyString The signing key.
     * @return The Base64-encoded signature.
     */
    private String doSign(String toSign, String keyString) {
        try {
            SecretKeySpec key = new SecretKeySpec(keyString.getBytes(CHARSET), HMAC_SHA1);
            Mac mac = Mac.getInstance(HMAC_SHA1);
            mac.init(key);
            byte[] bytes = mac.doFinal(toSign.getBytes(CHARSET));

            String base64 = new String(Base64.getEncoder().encode(bytes), CHARSET);
            return base64.replace("\r\n", "");
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Error Signing...", e);
        }
    }

    /**
     * Creates a new SignatureBuilder instance that is thread-safe.
     *
     * @return A new SignatureBuilder.
     */
    public SignatureBuilder signatureBuilder() {
        return new SignatureBuilder();
    }

    public enum Method {
        GET, POST, DELETE
    }

    /**
     * Builder class for constructing OAuth-signed requests.
     * Unlike the typical builder pattern, this class does not have one final build() method.
     * Instead, it provides methods to build the final URL and Authorization header separately.
     */
    public class SignatureBuilder {

        private final Map<String, String> oauthParameters = new HashMap<>();
        private final Map<String, String> queryParameters = new HashMap<>();
        private String url = null;
        private Method method = null;

        /**
         * Sets the URL for the request.
         * If the provided URL does not start with "http", it is treated as a relative path
         * and appended to the base URL.
         *
         * @param url The URL or relative path.
         * @return The current SignatureBuilder instance.
         */
        public SignatureBuilder url(String url) {
            if (url.startsWith("http")) {
                this.url = url;
            } else {
                this.url = baseUrl + url;
            }
            return this;
        }

        /**
         * Sets the URL for the request by concatenating the string representations of the provided objects.
         *
         * @param objects The objects to concatenate into a URL or relative path.
         * @return The current SignatureBuilder instance.
         */
        public SignatureBuilder uri(Object... objects) {
            return this.url(Arrays.stream(objects).map(Object::toString).collect(Collectors.joining("")));
        }

        /**
         * Sets the HTTP method (verb) for the request.
         *
         * @param method The HTTP method.
         * @return The current SignatureBuilder instance.
         */
        public SignatureBuilder verb(Method method) {
            this.method = method;
            return this;
        }

        /**
         * Adds an OAuth parameter to the request.
         *
         * @param key   The parameter key.
         * @param value The parameter value ( calls .name() to get value).
         * @return The current SignatureBuilder instance.
         */
        public SignatureBuilder parameter(String key, Enum<?> value) {
            if (value != null) {
                this.queryParameters.put(key, value.name());
            }
            return this;
        }

        /**
         * Adds an OAuth parameter to the request.
         *
         * @param key   The parameter key.
         * @param value The parameter value ( converts to true|false).
         * @return The current SignatureBuilder instance.
         */
        public SignatureBuilder parameter(String key, Boolean value) {
            if (value != null) {
                this.queryParameters.put(key, value ? "true" : "false");
            }
            return this;
        }

        /**
         * Adds an OAuth parameter to the request.
         *
         * @param key   The parameter key.
         * @param value The parameter value.
         * @return The current SignatureBuilder instance.
         */
        public SignatureBuilder parameter(String key, String value) {
            if (value != null) {
                this.queryParameters.put(key, value);
            }
            return this;
        }

        /**
         * Adds an OAuth parameter to the request.
         *
         * @param key   The parameter key.
         * @param value The parameter value.
         * @return The current SignatureBuilder instance.
         */
        public SignatureBuilder parameter(String key, Integer value) {
            if (value != null) {
                this.queryParameters.put(key, String.valueOf(value));
            }
            return this;
        }

        /**
         * Adds multiple OAuth parameters to the request.
         *
         * @param parameters A map of parameter key-value pairs.
         * @return The current SignatureBuilder instance.
         */
        public SignatureBuilder parameters(Map<String, String> parameters) {
            if (parameters != null) {
                this.queryParameters.putAll(parameters);
            }
            return this;
        }

        /**
         * Builds the final URL with query parameters.
         *
         * @return The complete URL as a string.
         */
        public String buildUrl() {
            if (queryParameters.isEmpty()) {
                return this.url;
            } else {
                StringBuilder url = new StringBuilder(this.url);
                if (!this.url.endsWith("?")) {
                    url.append("?");
                }
                boolean first = true;
                for (Entry<String, String> entry : queryParameters.entrySet()) {
                    if (!first) {
                        url.append("&");
                    }
                    url.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), CHARSET));
                    first = false;
                }
                return url.toString();
            }

        }

        /**
         * Builds the OAuth Authorization header. This should be included in the HTTP request headers with the
         * key named "Authorization"
         *
         * @return The Authorization header value.
         */
        public String buildAuthorizationHeader() {
            Map<String, String> fullParameters = getFinalOAuthParams();
            StringBuilder header = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String, String> entry : fullParameters.entrySet()) {
                if (entry.getKey().startsWith(BlinkAuthSigner.PARAM_PREFIX)) {
                    if (!first) header.append(", ");
                    header.append(entry.getKey()).append("=\"").append(URLEncoder.encode(entry.getValue(), CHARSET)).append("\"");
                    first = false;
                }
            }
            return HEADER_VALUES_PREFIX + header.toString();
        }

        /**
         * Computes the final OAuth parameters including the signature.
         * @return A map of all OAuth parameters including the signature.
         */
        private Map<String, String> getFinalOAuthParams() {
            //The computeSignature method also adds required OAuth parameters, so do this first!
            oauthParameters.put(VERSION, VERSION_VALUE);
            //Get current timestamp in seconds
            oauthParameters.put(TIMESTAMP, String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())));
            //Generate a unique nonce
            oauthParameters.put(NONCE, String.valueOf(System.currentTimeMillis() + Math.abs(random.nextInt())));
            oauthParameters.put(TOKEN, tokenValue);
            oauthParameters.put(CONSUMER_KEY, consumerKey);
            oauthParameters.put(SIGN_METHOD, SIGN_METHOD_VALUE);

            Map<String, String> params = new TreeMap<>();
            params.put(SIGNATURE, doSign(getBaseString(), oAuthEncode(consumerSecret) + '&' + oAuthEncode(tokenSecret)));
            params.putAll(oauthParameters);
            return params;
        }

        /**
         * Constructs the OAuth base string for signature generation.
         * @return The OAuth base string.
         */
        private String getBaseString() {
            List<String> params = new ArrayList<>();

            for (Entry<String, String> entry : oauthParameters.entrySet()) {
                params.add(oAuthEncode(entry.getKey()).concat("=").concat(entry.getValue()));
            }

            for (Entry<String, String> entry : queryParameters.entrySet()) {
                params.add(oAuthEncode(entry.getKey()).concat("=").concat(entry.getValue()));
            }

            Collections.sort(params);

            StringBuilder builder = new StringBuilder();
            for (String param : params) {
                builder.append('&').append(param);
            }

            String formUrlEncodedParams = oAuthEncode(builder.substring(1));
            String sanitizedUrl = oAuthEncode(url.replaceAll("\\?.*", "").replace("\\:\\d{4}", ""));

            return String.format("%s&%s&%s", method, sanitizedUrl, formUrlEncodedParams);
        }
    }
}
