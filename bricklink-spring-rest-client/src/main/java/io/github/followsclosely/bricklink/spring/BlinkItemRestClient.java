package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.BlinkItemClient;
import io.github.followsclosely.bricklink.dto.BlinkItem;
import io.github.followsclosely.bricklink.dto.BlinkItemType;
import io.github.followsclosely.bricklink.dto.BlinkPriceGuide;
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

    @Override
    public BlinkResponse<BlinkPriceGuide> getPriceGuide(BlinkItemType type, String number, PriceGuideQuery priceGuideQuery) {

        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
            .verb(BlinkAuthSigner.Method.GET)
            .uri("items/" + type.name() + "/" + number + "/price");

        if (priceGuideQuery != null) {
            signatureBuilder.parameter("color_id", priceGuideQuery.getColorId());
            if (priceGuideQuery.getGuideType() != null) signatureBuilder.parameter("guide_type", priceGuideQuery.getGuideType().getValue());
            if (priceGuideQuery.getNewOrUsed() != null) signatureBuilder.parameter("new_or_used", priceGuideQuery.getNewOrUsed().getValue());
            signatureBuilder.parameter("country_code", priceGuideQuery.getCountryCode());
            if (priceGuideQuery.getRegion() != null) signatureBuilder.parameter("region", priceGuideQuery.getRegion().getValue());
            signatureBuilder.parameter("currency_code", priceGuideQuery.getCurrencyCode());
            if (priceGuideQuery.getVat() != null) signatureBuilder.parameter("vat", priceGuideQuery.getVat().getValue());
        }

        return restClient.get()
            .uri(signatureBuilder.buildUrl())
            .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
            .retrieve().body(TypeReferences.BLINK_PRICE_GUIDE);
    }
}
