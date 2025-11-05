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
 * See: <a href="https://www.bricklink.com/v3/api.page?page=get-category-list">Bricklink API Guide - Get Category List</a>
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlinkCategory {

    @JsonProperty("category_id")
    private Long id;

    @JsonProperty("category_name")
    private String name;

    @JsonProperty("parent_id")
    private Long parentId;
}
