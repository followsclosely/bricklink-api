package io.github.followsclosely.bricklink.catalog.dto;

import lombok.*;

/**
 * Represents a part in the Bricklink database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"number", "name"})
public class BlinkPartDetails {
    private Long categoryId;
    private String categoryName;
    private String number;
    private String name;
    private String alternateItemNumber;
    private Double weightGrams;
    private String dimensions;
}