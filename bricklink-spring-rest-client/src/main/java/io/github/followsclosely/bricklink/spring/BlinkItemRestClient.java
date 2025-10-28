package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.BlinkItemClient;
import io.github.followsclosely.bricklink.dto.BlinkItem;
import io.github.followsclosely.bricklink.dto.BlinkItemType;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import io.github.followsclosely.bricklink.oauth.BlinkAuthSigner;
import org.springframework.web.client.RestClient;

public class BlinkItemRestClient extends AbstractBlinkRestClient implements BlinkItemClient {

    public BlinkItemRestClient(BlinkAuthSigner blinkAuthSigner) {
        super(blinkAuthSigner);
    }

    public BlinkItemRestClient(RestClient restClient, BlinkAuthSigner blinkAuthSigner) {
        super(blinkAuthSigner, restClient);
    }

    public BlinkResponse<BlinkItem> getItem(BlinkItemType type, String number) {
        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("items/" + type.name() + "/" + number);

        return restClient.get()
                .uri(signatureBuilder.buildUrl())
                .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
                .retrieve().body(TypeReferences.BLINK_CATALOG_ITEM);
    }
}

