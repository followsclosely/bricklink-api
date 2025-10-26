package io.github.followsclosely.bricklink.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * Represents a book in the Bricklink database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(of = {"number", "name"})
public class BlinkBook {
    private Long categoryId;
    private String categoryName;
    private String number;
    private String name;
    private Integer yearReleased;
    private Double weightGrams;
    private String dimensions;
}