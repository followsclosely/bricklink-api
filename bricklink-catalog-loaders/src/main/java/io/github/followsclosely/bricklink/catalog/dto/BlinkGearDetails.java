package io.github.followsclosely.bricklink.catalog.dto;

import lombok.*;

/**
 * Represents a gear item in the Bricklink database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"number", "name"})
public class BlinkGearDetails {
    private Long categoryId;
    private String categoryName;
    private String number;
    private String name;
    private Integer yearReleased;
    private Double weightGrams;
    private String dimensions;
}

