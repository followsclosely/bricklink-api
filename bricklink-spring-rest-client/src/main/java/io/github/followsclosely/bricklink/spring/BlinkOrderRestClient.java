package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.BlinkOrderClient;
import io.github.followsclosely.bricklink.dto.BlinkOrder;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import io.github.followsclosely.bricklink.oauth.BlinkAuthSigner;
import org.springframework.web.client.RestClient;

import java.util.List;

public class BlinkOrderRestClient extends AbstractBlinkRestClient implements BlinkOrderClient {

    public BlinkOrderRestClient(BlinkAuthSigner blinkAuthSigner) {
        super(blinkAuthSigner);
    }

    public BlinkOrderRestClient(RestClient restClient, BlinkAuthSigner blinkAuthSigner) {
        super(blinkAuthSigner, restClient);
    }

    @Override
    public BlinkResponse<BlinkOrder> getOrder(Integer id) {

        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("orders/" + id);

        return restClient.get()
                .uri(signatureBuilder.buildUrl())
                .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
                .retrieve().body(TypeReferences.BLINK_ORDER);
    }

    @Override
    public BlinkResponse<List<BlinkOrder>> getOrders(BlinkOrderClient.Query query) {

        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("orders");

        if (query != null) {
            signatureBuilder.parameter("direction", query.getDirection());
            signatureBuilder.parameter("status", query.getStatus());
            signatureBuilder.parameter("filed", query.getFiled());
        }

        return restClient.get()
                .uri(signatureBuilder.buildUrl())
                .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
                .retrieve().body(TypeReferences.BLINK_ORDER_LIST);
    }
}
