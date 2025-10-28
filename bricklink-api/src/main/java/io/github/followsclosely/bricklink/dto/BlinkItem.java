package io.github.followsclosely.bricklink.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(of = {"number", "type", "name"})
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

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BlinkPriceGuide {
        @JsonProperty("item")
        private BlinkItem item;
        @JsonProperty("new_or_used")
        private String newOrUsed;
        @JsonProperty("currency_code")
        private String currencyCode;
        @JsonProperty("min_price")
        private Double minPrice;
        @JsonProperty("max_price")
        private Double maxPrice;
        @JsonProperty("avg_price")
        private Double avgPrice;
        @JsonProperty("qty_avg_price")
        private Double qtyAvgPrice;
        @JsonProperty("unit_quantity")
        private Integer unitQuantity;
        @JsonProperty("total_quantity")
        private Integer totalQuantity;
        @JsonProperty("price_detail")
        private List<PriceDetail> priceDetail;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class KnownColor{
        @JsonProperty("color_id")
        private String colorId;
        @JsonProperty("quantity")
        private Integer quantity;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Image {
        @JsonProperty("thumbnail_url")
        private String thumbnailUrl;
        @JsonProperty("type")
        private String type;
        @JsonProperty("no")
        private String number;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ElementIdMapping {
        @JsonProperty("item")
        private Item item;
        @JsonProperty("color_id")
        private String colorId;
        @JsonProperty("element_id")
        private String elementId;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Item {
            @JsonProperty("no")
            private String number;
            @JsonProperty("type")
            private BlinkItemType type;
        }
    }
}