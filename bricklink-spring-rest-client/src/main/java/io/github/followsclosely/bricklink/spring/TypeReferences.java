package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.dto.BlinkItem;
import io.github.followsclosely.bricklink.dto.BlinkColor;
import io.github.followsclosely.bricklink.dto.BlinkOrder;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import io.github.followsclosely.bricklink.dto.BlinkPriceGuide;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class TypeReferences {
    public final static ParameterizedTypeReference<BlinkResponse<BlinkColor>> BLINK_COLOR = new ParameterizedTypeReference<>() {
    };
    public final static ParameterizedTypeReference<BlinkResponse<List<BlinkColor>>> BLINK_COLOR_LIST = new ParameterizedTypeReference<>() {
    };
    public final static ParameterizedTypeReference<BlinkResponse<BlinkOrder>> BLINK_ORDER = new ParameterizedTypeReference<>() {
    };
    public final static ParameterizedTypeReference<BlinkResponse<List<BlinkOrder>>> BLINK_ORDER_LIST = new ParameterizedTypeReference<>() {
    };
    public final static ParameterizedTypeReference<BlinkResponse<List<List<BlinkOrder.OrderItem>>>> BLINK_ORDER_ITEMS_LIST = new ParameterizedTypeReference<>() {
    };
    public final static ParameterizedTypeReference<BlinkResponse<List<BlinkOrder.Message>>> BLINK_ORDER_MESSAGES_LIST = new ParameterizedTypeReference<>() {
    };
    public final static ParameterizedTypeReference<BlinkResponse<List<BlinkOrder.Feedback>>> BLINK_ORDER_FEEDBACK_LIST = new ParameterizedTypeReference<>() {
    };
    public final static ParameterizedTypeReference<BlinkResponse<BlinkItem>> BLINK_CATALOG_ITEM = new ParameterizedTypeReference<>() {
    };
    public final static ParameterizedTypeReference<BlinkResponse<BlinkPriceGuide>> BLINK_PRICE_GUIDE = new ParameterizedTypeReference<>() {
    };
}
