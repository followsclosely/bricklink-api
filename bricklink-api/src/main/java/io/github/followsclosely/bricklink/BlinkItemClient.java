package io.github.followsclosely.bricklink;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.followsclosely.bricklink.dto.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Client interface for interacting with Bricklink items.
 *
 * This interface provides methods to retrieve item details, price guides, known colors, images, and element ID mappings
 * for various types of items available on Bricklink, such as sets, parts, and minifigures.
 *
 * @see <a href="https://www.bricklink.com/v3/api.page?page=get-item">Bricklink API Guide - Get Item</a>
 * @see <a href="https://www.bricklink.com/v3/api.page?page=get-price-guide">Bricklink API Guide - Get Price Guide</a>
 * @see <a href="https://www.bricklink.com/v3/api.page?page=get-known-colors">Bricklink API Guide - Get Known Colors</a>
 * @see <a href="https://www.bricklink.com/v3/api.page?page=get-item-image">Bricklink API Guide - Get Item Image</a>
 * @see <a href="https://www.bricklink.com/v3/api.page?page=get-item-element-ids">Bricklink API Guide - Get Item Element IDs</a>
 *
 */
public interface BlinkItemClient {

    /**
     * Retrieves a specific item by its type and number.
     *
     * @param type   The type of the item (e.g., SET, PART, MINIFIGURE).
     * @param number The unique identifier for the item.
     * @return A BlinkResponse containing the item details.
     * @see <a href="https://www.bricklink.com/v3/api.page?page=get-item">Bricklink API Guide - Get Item</a>
     */
    BlinkResponse<BlinkItem> getItem(BlinkItem.Type type, String number );

    /**
     * Retrieves the price guide for a specific item type and number, with optional query parameters.
     *
     * @param type            The type of the item (e.g., SET, PART, MINIFIGURE).
     * @param number          The unique identifier for the item.
     * @param priceGuideQuery Additional query parameters to customize the price guide retrieval.
     * @return A BlinkResponse containing the price guide information associated with the specified item.
     * @see <a href="https://www.bricklink.com/v3/api.page?page=get-price-guide">Bricklink API Guide - Get Price Guide</a>
     */
    BlinkResponse<BlinkItem.BlinkPriceGuide> getPriceGuide(BlinkItem.Type type, String number, PriceGuideQuery priceGuideQuery);

    @Data
    @Builder
    class PriceGuideQuery {
        private Integer colorId;
        private GuideType guideType;
        private Condition condition;
        private String countryCode;
        private Region region;
        private String currencyCode;
        private VatOption vat;

        @RequiredArgsConstructor
        public enum Condition {
            NEW("N"),
            USED("U");

            private final String value;
            @JsonValue
            public String getValue() { return value; }
            @JsonCreator
            public static Condition fromValue(String value) {
                for (Condition n : values()) {
                    if (n.value.equalsIgnoreCase(value)) return n;
                }
                return null;
            }
        }

        @RequiredArgsConstructor
        public enum GuideType {
            SOLD("sold"),
            STOCK("stock");

            private final String value;
            @JsonValue public String getValue() { return value; }
            @JsonCreator public static GuideType fromValue(String value) {
                for (GuideType g : values()) {
                    if (g.value.equalsIgnoreCase(value)) return g;
                }
                return null;
            }
        }

        @RequiredArgsConstructor
        public enum Region {
            ASIA("asia"),
            AFRICA("africa"),
            NORTH_AMERICA("north_america"),
            SOUTH_AMERICA("south_america"),
            MIDDLE_EAST("middle_east"),
            EUROPE("europe"),
            EU("eu"),
            OCEANIA("oceania");

            private final String value;

            @JsonValue public String getValue() { return value; }
            @JsonCreator public static Region fromValue(String value) {
                for (Region r : values()) {
                    if (r.value.equalsIgnoreCase(value)) return r;
                }
                return null;
            }
        }

        @RequiredArgsConstructor
        public enum VatOption {
            EXCLUDE("N"),
            INCLUDE("Y"),
            NORWAY("O");

            private final String value;
            @JsonValue public String getValue() { return value; }
            @JsonCreator public static VatOption fromValue(String value) {
                for (VatOption v : values()) {
                    if (v.value.equalsIgnoreCase(value)) return v;
                }
                return null;
            }
        }
    }

    /**
     * Retrieves the known colors for a specific item type and number.
     *
     * @param type   The type of the item (e.g., SET, PART, MINIFIGURE).
     * @param number The unique identifier for the item.
     * @return A BlinkResponse containing a list of known colors associated with the specified item.
     * @see <a href="https://www.bricklink.com/v3/api.page?page=get-known-colors">Bricklink API Guide - Get Known Colors</a>
     */
    BlinkResponse<List<BlinkItem.KnownColor>> getKnownColors(BlinkItem.Type type, String number);

    /**
     * Retrieves the image information for a specific item type, number, and optional color.
     *
     * @param type   The type of the item (e.g., SET, PART, MINIFIGURE).
     * @param number The unique identifier for the item.
     * @param color  (Optional) The color ID to retrieve the image for a specific color variant of the item.
     * @return A BlinkResponse containing the image information associated with the specified item.
     * @see <a href="https://www.bricklink.com/v3/api.page?page=get-item-image">Bricklink API Guide - Get Item Image</a>
     */
    BlinkResponse<BlinkItem.Image> getImage(BlinkItem.Type type, String number, Integer color);

    /**
     * Retrieves the element ID mappings for a specific item type and number.
     *
     * @param type   The type of the item (e.g., SET, PART, MINIFIGURE).
     * @param number The unique identifier for the item.
     * @return A BlinkResponse containing a list of element ID mappings associated with the specified item.
     * @see <a href="https://www.bricklink.com/v3/api.page?page=get-item-element-ids">Bricklink API Guide - Get Item Element IDs</a>
     */
    BlinkResponse<List<BlinkItem.ElementIdMapping>> getElementId(BlinkItem.Type type, String number);
}
