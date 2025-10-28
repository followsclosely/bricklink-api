package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.dto.BlinkColor;
import io.github.followsclosely.bricklink.dto.BlinkOrder;
import io.github.followsclosely.bricklink.dto.BlinkOrderFeedback;
import io.github.followsclosely.bricklink.dto.BlinkOrderItem;
import io.github.followsclosely.bricklink.dto.BlinkOrderMessage;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
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
    public final static ParameterizedTypeReference<BlinkResponse<List<List<BlinkOrderItem>>>> BLINK_ORDER_ITEMS_LIST = new ParameterizedTypeReference<>() {
    };
    public final static ParameterizedTypeReference<BlinkResponse<List<BlinkOrderMessage>>> BLINK_ORDER_MESSAGES_LIST = new ParameterizedTypeReference<>() {
    };
    public final static ParameterizedTypeReference<BlinkResponse<List<BlinkOrderFeedback>>> BLINK_ORDER_FEEDBACK_LIST = new ParameterizedTypeReference<>() {
    };
}
