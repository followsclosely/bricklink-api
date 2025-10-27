package io.github.followsclosely.bricklink.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a color in the Bricklink database.
 * <p>
 * See: <a href="https://www.bricklink.com/v3/api.page?page=resource-representations-color">Bricklink Color: Resource Representations</a>
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlinkColor {
    @JsonProperty("color_id")
    private Long id;
    @JsonProperty("color_name")
    private String name;
    @JsonProperty("color_code")
    private String hex;
    @JsonProperty("color_type")
    private String type;
}
