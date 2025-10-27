package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.oauth.BlinkAuthSigner;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class AbstractBlinkRestClient {
    public static final String API_BASE_URL = "https://api.bricklink.com/api/store/v1/";

    protected final BlinkAuthSigner blinkAuthSigner;
    protected final RestClient restClient;

    public AbstractBlinkRestClient(BlinkAuthSigner blinkAuthSigner) {
        this(blinkAuthSigner, RestClient.builder().baseUrl(API_BASE_URL).build());
    }
}
