package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.dto.BlinkColor;
import io.github.followsclosely.bricklink.dto.BlinkItem;
import io.github.followsclosely.bricklink.dto.BlinkOrder;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

/**
 * Holds ParameterizedTypeReference constants for various BlinkResponse types.
 * These references are used for deserializing API responses with generic types.
 * @see BlinkResponse
 */
public class TypeReferences {
    // @formatter:off
    public final static ParameterizedTypeReference<BlinkResponse<BlinkColor>> BLINK_COLOR = new ParameterizedTypeReference<>() {};
    public final static ParameterizedTypeReference<BlinkResponse<List<BlinkColor>>> BLINK_COLOR_LIST = new ParameterizedTypeReference<>() {};
    public final static ParameterizedTypeReference<BlinkResponse<BlinkOrder>> BLINK_ORDER = new ParameterizedTypeReference<>() {};
    public final static ParameterizedTypeReference<BlinkResponse<List<BlinkOrder>>> BLINK_ORDER_LIST = new ParameterizedTypeReference<>() {};
    public final static ParameterizedTypeReference<BlinkResponse<List<List<BlinkOrder.OrderItem>>>> BLINK_ORDER_ITEMS_LIST = new ParameterizedTypeReference<>() {};
    public final static ParameterizedTypeReference<BlinkResponse<List<BlinkOrder.Message>>> BLINK_ORDER_MESSAGES_LIST = new ParameterizedTypeReference<>() {};
    public final static ParameterizedTypeReference<BlinkResponse<List<BlinkOrder.Feedback>>> BLINK_ORDER_FEEDBACK_LIST = new ParameterizedTypeReference<>() {};
    public final static ParameterizedTypeReference<BlinkResponse<BlinkItem>> BLINK_CATALOG_ITEM = new ParameterizedTypeReference<>() {};
    public final static ParameterizedTypeReference<BlinkResponse<BlinkItem.BlinkPriceGuide>> BLINK_ITEM_PRICE_GUIDE = new ParameterizedTypeReference<>() {};
    public final static ParameterizedTypeReference<BlinkResponse<List<BlinkItem.KnownColor>>> BLINK_ITEM_KNOWN_COLOR = new ParameterizedTypeReference<>() {};
    public final static ParameterizedTypeReference<BlinkResponse<BlinkItem.Image>> BLINK_ITEM_IMAGE = new ParameterizedTypeReference<>() {};
    public final static ParameterizedTypeReference<BlinkResponse<List<BlinkItem.ElementIdMapping>>> BLINK_ITEM_ELEMENT_ID_MAPPING = new ParameterizedTypeReference<>() {};
    // @formatter:on
}
