package io.github.followsclosely.bricklink.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ToString(of = {"number", "type", "name"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlinkItem {
    @JsonProperty("no")
    private String number;
    @JsonProperty("type")
    private Type type;
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

    /**
     * Enumeration representing different types of items in the BrickLink catalog.<br>
     * Each item type has a unique identifier and a descriptive name.<br>
     * Provides a lookup method to retrieve an ItemType by its identifier.
     */
    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Type {

        SET("S", "Set"),
        PART("P", "Part"),
        MINIFIGURE("M", "Minifigure"),
        BOOK("B", "Book"),
        GEAR("G", "Gear"),
        CATALOG("C", "Catalog"),
        INSTRUCTION("I", "Instruction"),
        ORIGINAL_BOX("O", "Original Box"),
        UNSORTED_LOT("U", "Unsorted Lot"),
        UNKNOWN("?", "Unknown");

        private static final Map<String, Type> ID_MAP;

        static {
            Map<String, Type> map = new HashMap<>();
            for (Type type : values()) {
                map.put(type.name(), type);
            }
            ID_MAP = Collections.unmodifiableMap(map);
        }

        /**
         * The unique identifier for the item type (e.g., "S" for Set).
         */
        private final String value;
        /**
         * The descriptive name of the item type (e.g., "Set").
         */
        private final String displayName;

        /**
         * Returns the {@code ItemType} corresponding to the given identifier.<br>
         * If the identifier does not match any known item type, {@link #UNKNOWN} is returned.
         *
         * @param id the item type identifier (e.g., "S", "P")
         * @return the matching {@code ItemType}, or {@link #UNKNOWN} if not found
         */
        @JsonCreator
        public static Type fromId(String id) {
            return ID_MAP.getOrDefault(id, UNKNOWN);
        }
    }

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
    public static class KnownColor {
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
            private Type type;
        }
    }
}