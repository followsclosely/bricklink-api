package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.BlinkColorClient;
import io.github.followsclosely.bricklink.dto.BlinkColor;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import io.github.followsclosely.bricklink.oauth.BlinkAuthSigner;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * REST client implementation for Bricklink colors.
 */
public class BlinkColorRestClient extends AbstractBlinkRestClient implements BlinkColorClient {

    public BlinkColorRestClient(BlinkAuthSigner blinkAuthSigner) {
        super(blinkAuthSigner);
    }

    public BlinkColorRestClient(RestClient restClient, BlinkAuthSigner blinkAuthSigner) {
        super(blinkAuthSigner, restClient);
    }

    @Override
    public BlinkResponse<BlinkColor> getColor(Integer id) {

        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("colors/" + id);

        return restClient.get()
                .uri(signatureBuilder.buildUrl())
                .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
                .retrieve().body(TypeReferences.BLINK_COLOR);
    }

    @Override
    public BlinkResponse<List<BlinkColor>> getColors() {

        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("colors");

        return restClient.get()
                .uri(signatureBuilder.buildUrl())
                .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
                .retrieve().body(TypeReferences.BLINK_COLOR_LIST);
    }
}
