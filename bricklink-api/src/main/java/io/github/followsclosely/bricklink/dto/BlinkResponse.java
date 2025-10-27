package io.github.followsclosely.bricklink.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlinkResponse<D> {
    @JsonProperty("data")
    private D data;
    @JsonProperty("meta")
    private MetaData metaData;

    @Data
    public static class MetaData {
        String description;
        String message;
        Integer code;
    }
}


