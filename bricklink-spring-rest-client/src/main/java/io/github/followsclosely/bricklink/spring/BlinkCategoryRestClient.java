package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.BlinkCategoryClient;
import io.github.followsclosely.bricklink.dto.BlinkCategory;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import io.github.followsclosely.bricklink.oauth.BlinkAuthSigner;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * REST client implementation for Bricklink colors.
 */
public class BlinkCategoryRestClient extends AbstractBlinkRestClient implements BlinkCategoryClient {

    public BlinkCategoryRestClient(BlinkAuthSigner blinkAuthSigner) {
        super(blinkAuthSigner);
    }

    public BlinkCategoryRestClient(BlinkAuthSigner blinkAuthSigner, RestClient restClient) {
        super(blinkAuthSigner, restClient);
    }

    @Override
    public BlinkResponse<BlinkCategory> getCategory(Long id) {

        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("categories/" + id);

        String json = restClient.get()
                .uri(signatureBuilder.buildUrl())
                .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
                .retrieve().body(String.class);

        return restClient.get()
                .uri(signatureBuilder.buildUrl())
                .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
                .retrieve().body(TypeReferences.BLINK_CATEGORY);
    }

    @Override
    public BlinkResponse<List<BlinkCategory>> getCategories() {

        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("categories");

        return restClient.get()
                .uri(signatureBuilder.buildUrl())
                .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
                .retrieve().body(TypeReferences.BLINK_CATEGORY_LIST);
    }
}
