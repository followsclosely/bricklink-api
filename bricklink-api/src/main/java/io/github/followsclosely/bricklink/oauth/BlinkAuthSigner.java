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
     * <p>
     * Note: This method ensures that the base URL ends with a trailing slash.
     * </p>
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        if (!baseUrl.endsWith("/")) {
            this.baseUrl += "/";
        }
    }

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

    public SignatureBuilder signatureBuilder() {
        return new SignatureBuilder();
    }

    public enum Method {
        GET, POST, DELETE
    }

    public class SignatureBuilder {

        private final Map<String, String> oauthParameters = new HashMap<>();
        private final Map<String, String> queryParameters = new HashMap<>();
        private String url = null;
        private Method method = null;

        public SignatureBuilder url(String url) {
            if (url.startsWith("http")) {
                this.url = url;
            } else {
                this.url = baseUrl + url;
            }
            return this;
        }

        public SignatureBuilder uri(Object... objects) {
            this.url = Arrays.stream(objects).map(Object::toString).collect(Collectors.joining(""));
            return this;
        }

        public SignatureBuilder verb(Method method) {
            this.method = method;
            return this;
        }

        public SignatureBuilder parameter(String key, Enum<?> value) {
            if (value != null) {
                this.queryParameters.put(key, value.name());
            }
            return this;
        }

        public SignatureBuilder parameter(String key, Boolean value) {
            if (value != null) {
                this.queryParameters.put(key, value ? "true" : "false");
            }
            return this;
        }

        public SignatureBuilder parameter(String key, String value) {
            if (value != null) {
                this.queryParameters.put(key, value);
            }
            return this;
        }

        public SignatureBuilder parameters(Map<String, String> parameters) {
            if (parameters != null) {
                this.queryParameters.putAll(parameters);
            }
            return this;
        }

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

        public Map<String, String> getFinalOAuthParams() {
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
