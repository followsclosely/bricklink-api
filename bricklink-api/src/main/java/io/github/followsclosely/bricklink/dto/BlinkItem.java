package io.github.followsclosely.bricklink.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlinkItem {
    @JsonProperty("no")
    private String number;
    @JsonProperty("type")
    private BlinkItemType type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("category_id")
    private Integer categoryId;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("year_released")
    private Integer yearReleased;
    @JsonProperty("description")
    private String description;
}