package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.BlinkItemClient;
import io.github.followsclosely.bricklink.dto.BlinkItem;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import io.github.followsclosely.bricklink.oauth.BlinkAuthSigner;
import org.springframework.web.client.RestClient;

import java.util.List;

import static io.github.followsclosely.bricklink.spring.TypeReferences.*;

/**
 * REST client implementation for Bricklink items.
 */
public class BlinkItemRestClient extends AbstractBlinkRestClient implements BlinkItemClient {

    public BlinkItemRestClient(BlinkAuthSigner blinkAuthSigner) {
        super(blinkAuthSigner);
    }

    public BlinkItemRestClient(RestClient restClient, BlinkAuthSigner blinkAuthSigner) {
        super(blinkAuthSigner, restClient);
    }

    public BlinkResponse<BlinkItem> getItem(BlinkItem.Type type, String number) {
        BlinkAuthSigner.SignatureBuilder signature = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("items/" + type.name() + "/" + number);

        return restClient.get()
                .uri(signature.buildUrl())
                .header(BlinkAuthSigner.HEADER, signature.buildAuthorizationHeader())
                .retrieve().body(BLINK_CATALOG_ITEM);
    }

    public BlinkResponse<List<BlinkItem.SubsetEntry>> getItemSubsets(BlinkItem.Type type, String number, ItemSubsetsQuery query) {
        BlinkAuthSigner.SignatureBuilder signature = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("items/" + type.name() + "/" + number + "/subsets");

        if (query != null) {
            if (query.getColorId() != null) signature.parameter("color_id", query.getColorId());
            if (query.getBox() != null) signature.parameter("box", query.getBox());
            if (query.getInstructions() != null) signature.parameter("instruction", query.getInstructions());
            if (query.getBreakMinifigs() != null) signature.parameter("break_minifigs", query.getBreakMinifigs());
            if (query.getBreakSubsets() != null) signature.parameter("break_subsets", query.getBreakSubsets());
        }

        return restClient.get()
                .uri(signature.buildUrl())
                .header(BlinkAuthSigner.HEADER, signature.buildAuthorizationHeader())
                .retrieve().body(BLINK_ITEM_SUBSET_ENTRY_LIST);
    }

    @Override
    public BlinkResponse<BlinkItem.BlinkPriceGuide> getPriceGuide(BlinkItem.Type type, String number, PriceGuideQuery query) {

        BlinkAuthSigner.SignatureBuilder signature = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("items/" + type.name() + "/" + number + "/price");

        if (query != null) {
            signature.parameter("color_id", query.getColorId());
            if (query.getGuideType() != null) signature.parameter("guide_type", query.getGuideType().getValue());
            if (query.getCondition() != null) signature.parameter("new_or_used", query.getCondition().getValue());
            signature.parameter("country_code", query.getCountryCode());
            if (query.getRegion() != null) signature.parameter("region", query.getRegion().getValue());
            signature.parameter("currency_code", query.getCurrencyCode());
            if (query.getVat() != null) signature.parameter("vat", query.getVat().getValue());
        }

        return restClient.get()
                .uri(signature.buildUrl())
                .header(BlinkAuthSigner.HEADER, signature.buildAuthorizationHeader())
                .retrieve().body(BLINK_ITEM_PRICE_GUIDE);
    }

    @Override
    public BlinkResponse<List<BlinkItem.KnownColor>> getKnownColors(BlinkItem.Type type, String number) {
        BlinkAuthSigner.SignatureBuilder signature = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("items/" + type.name() + "/" + number + "/colors");

        return restClient.get()
                .uri(signature.buildUrl())
                .header(BlinkAuthSigner.HEADER, signature.buildAuthorizationHeader())
                .retrieve().body(BLINK_ITEM_KNOWN_COLOR);
    }

    public BlinkResponse<BlinkItem.Image> getImage(BlinkItem.Type type, String number, Integer color) {
        BlinkAuthSigner.SignatureBuilder signature = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("items/" + type.name() + "/" + number + "/images/" + color);

        return restClient.get()
                .uri(signature.buildUrl())
                .header(BlinkAuthSigner.HEADER, signature.buildAuthorizationHeader())
                .retrieve().body(BLINK_ITEM_IMAGE);
    }

    public BlinkResponse<List<BlinkItem.ElementIdMapping>> getElementId(BlinkItem.Type type, String number) {
        BlinkAuthSigner.SignatureBuilder signature = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("item_mapping/" + type.name() + "/" + number);

        return restClient.get()
                .uri(signature.buildUrl())
                .header(BlinkAuthSigner.HEADER, signature.buildAuthorizationHeader())
                .retrieve().body(BLINK_ITEM_ELEMENT_ID_MAPPING);
    }

    public BlinkResponse<List<BlinkItem.ElementIdMapping>> getItemNumber(String elementId) {
        BlinkAuthSigner.SignatureBuilder signature = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri("item_mapping/" + elementId);

        return restClient.get()
                .uri(signature.buildUrl())
                .header(BlinkAuthSigner.HEADER, signature.buildAuthorizationHeader())
                .retrieve().body(BLINK_ITEM_ELEMENT_ID_MAPPING);
    }
}
