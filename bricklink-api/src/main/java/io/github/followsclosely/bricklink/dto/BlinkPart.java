package io.github.followsclosely.bricklink.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * Represents a part in the Bricklink database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(of = {"number", "name"})
public class BlinkPart {
    private Long categoryId;
    private String categoryName;
    private String number;
    private String name;
    private String alternateItemNumber;
    private Double weightGrams;
    private String dimensions;
}