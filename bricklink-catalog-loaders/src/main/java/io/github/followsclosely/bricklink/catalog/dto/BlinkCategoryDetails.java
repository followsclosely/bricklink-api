package io.github.followsclosely.bricklink.catalog.dto;

import lombok.*;

/**
 * Represents a category in the Bricklink database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "name"})
public class BlinkCategoryDetails {
    private Long id;
    private String name;
}

