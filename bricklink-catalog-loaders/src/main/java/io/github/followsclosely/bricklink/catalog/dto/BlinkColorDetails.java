package io.github.followsclosely.bricklink.catalog.dto;

import lombok.*;

/**
 * Represents a color in the Bricklink database.
 * Contains metadata for the color as well.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "name", "rgb"})
public class BlinkColorDetails {
    private Integer parts;
    private Integer inSets;
    private Integer wanted;
    private Integer forSale;
    private Integer yearFrom;
    private Integer yearTo;
    private String type;

    /**
     * The unique identifier of the color.
     */
    private Long id;
    /**
     * The name of the color.
     */
    private String name;
    /**
     * The RGB value of the color.
     */
    private String rgb;
}
