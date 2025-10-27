package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.dto.BlinkColor;
import io.github.followsclosely.bricklink.dto.BlinkOrder;
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
}
