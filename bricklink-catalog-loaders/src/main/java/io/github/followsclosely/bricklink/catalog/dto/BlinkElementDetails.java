package io.github.followsclosely.bricklink.catalog.dto;

import lombok.*;

/**
 * Represents an element code in the Bricklink database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"itemNo", "color", "code"})
public class BlinkElementDetails {
    private String itemNo;
    private String color;
    private String code;
}

