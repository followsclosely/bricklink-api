package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.BlinkOrderClient;
import io.github.followsclosely.bricklink.dto.BlinkOrder;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import io.github.followsclosely.bricklink.oauth.BlinkAuthSigner;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * REST client implementation for Bricklink items.
 */
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

    @Override
    public BlinkResponse<List<List<BlinkOrder.OrderItem>>> getOrderItems(Integer id) {

        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("orders/" + id + "/items");

        return restClient.get()
                .uri(signatureBuilder.buildUrl())
                .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
                .retrieve().body(TypeReferences.BLINK_ORDER_ITEMS_LIST);
    }

    @Override
    public BlinkResponse<List<BlinkOrder.Message>> getOrderMessages(Integer id) {

        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("orders/" + id + "/messages");

        return restClient.get()
                .uri(signatureBuilder.buildUrl())
                .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
                .retrieve().body(TypeReferences.BLINK_ORDER_MESSAGES_LIST);
    }

    @Override
    public BlinkResponse<List<BlinkOrder.Feedback>> getOrderFeedback(Integer orderId) {

        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("orders/" + orderId + "/feedback");

        return restClient.get()
                .uri(signatureBuilder.buildUrl())
                .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
                .retrieve().body(TypeReferences.BLINK_ORDER_FEEDBACK_LIST);
    }
}
