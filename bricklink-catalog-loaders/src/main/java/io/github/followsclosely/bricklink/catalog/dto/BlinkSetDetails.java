package io.github.followsclosely.bricklink.catalog.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BlinkSetDetails {
    Long categoryId;
    String categoryName;
    String number;
    String name;
    Integer yearReleased;
    Double weightGrams;
    String dimensions;
}