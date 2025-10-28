package io.github.followsclosely.bricklink.spring;

import static io.github.followsclosely.bricklink.spring.TypeReferences.*;
import io.github.followsclosely.bricklink.BlinkItemClient;
import io.github.followsclosely.bricklink.dto.BlinkItem;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import io.github.followsclosely.bricklink.oauth.BlinkAuthSigner;
import org.springframework.web.client.RestClient;

import java.util.List;

public class BlinkItemRestClient extends AbstractBlinkRestClient implements BlinkItemClient {

    public BlinkItemRestClient(BlinkAuthSigner blinkAuthSigner) {
        super(blinkAuthSigner);
    }

    public BlinkItemRestClient(RestClient restClient, BlinkAuthSigner blinkAuthSigner) {
        super(blinkAuthSigner, restClient);
    }

    public BlinkResponse<BlinkItem> getItem(BlinkItem.Type type, String number) {
        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("items/" + type.name() + "/" + number);

        return restClient.get()
                .uri(signatureBuilder.buildUrl())
                .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
                .retrieve().body(BLINK_CATALOG_ITEM);
    }

    @Override
    public BlinkResponse<BlinkItem.BlinkPriceGuide> getPriceGuide(BlinkItem.Type type, String number, PriceGuideQuery priceGuideQuery) {

        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
            .verb(BlinkAuthSigner.Method.GET)
            .uri("items/" + type.name() + "/" + number + "/price");

        if (priceGuideQuery != null) {
            signatureBuilder.parameter("color_id", priceGuideQuery.getColorId());
            if (priceGuideQuery.getGuideType() != null) signatureBuilder.parameter("guide_type", priceGuideQuery.getGuideType().getValue());
            if (priceGuideQuery.getCondition() != null) signatureBuilder.parameter("new_or_used", priceGuideQuery.getCondition().getValue());
            signatureBuilder.parameter("country_code", priceGuideQuery.getCountryCode());
            if (priceGuideQuery.getRegion() != null) signatureBuilder.parameter("region", priceGuideQuery.getRegion().getValue());
            signatureBuilder.parameter("currency_code", priceGuideQuery.getCurrencyCode());
            if (priceGuideQuery.getVat() != null) signatureBuilder.parameter("vat", priceGuideQuery.getVat().getValue());
        }

        return restClient.get()
            .uri(signatureBuilder.buildUrl())
            .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
            .retrieve().body(BLINK_ITEM_PRICE_GUIDE);
    }

    @Override
    public BlinkResponse<List<BlinkItem.KnownColor>> getKnownColors(BlinkItem.Type type, String number){
        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("items/" + type.name() + "/" + number + "/colors");

        return restClient.get()
                .uri(signatureBuilder.buildUrl())
                .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
                .retrieve().body(BLINK_ITEM_KNOWN_COLOR);
    }

    public BlinkResponse<BlinkItem.Image> getImage(BlinkItem.Type type, String number, Integer color){
        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("items/" + type.name() + "/" + number + "/images/" + color);

        return restClient.get()
                .uri(signatureBuilder.buildUrl())
                .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
                .retrieve().body(BLINK_ITEM_IMAGE);
    }

    public BlinkResponse<List<BlinkItem.ElementIdMapping>> getElementId(BlinkItem.Type type, String number) {
        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("item_mapping/" + type.name() + "/" + number);

        return restClient.get()
                .uri(signatureBuilder.buildUrl())
                .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
                .retrieve().body(BLINK_ITEM_ELEMENT_ID_MAPPING);
    }

    public BlinkResponse<List<BlinkItem.ElementIdMapping>> getItemNumber(String elementId) {
        BlinkAuthSigner.SignatureBuilder signatureBuilder = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("item_mapping/" + elementId);

        String json = restClient.get()
                .uri(signatureBuilder.buildUrl())
                .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
                .retrieve().body(String.class);

        return restClient.get()
                .uri(signatureBuilder.buildUrl())
                .header(BlinkAuthSigner.HEADER, signatureBuilder.buildAuthorizationHeader())
                .retrieve().body(BLINK_ITEM_ELEMENT_ID_MAPPING);
    }
}
