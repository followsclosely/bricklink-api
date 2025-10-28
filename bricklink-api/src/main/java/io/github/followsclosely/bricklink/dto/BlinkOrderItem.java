package io.github.followsclosely.bricklink.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an item in a Bricklink order.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlinkOrderItem {
    /**
     * The ID of the store inventory that includes the item.
     */
    private Integer inventoryId;

    /**
     * An object representation of the item.
     */
    private Item item;

    /**
     * The ID of the color of the item.
     */
    private Integer colorId;

    /**
     * Color name of the item. (Upcoming feature)
     */
    private String colorName;

    /**
     * The number of items purchased in this order.
     */
    private Integer quantity;

    /**
     * Indicates whether the item is new or used. N: New, U: Used
     */
    private String newOrUsed;

    /**
     * Indicates whether the set is complete or incomplete (valid only for SET type).
     * C: Complete, B: Incomplete, S: Sealed
     */
    private String completeness;

    /**
     * The original price of this item per sale unit.
     */
    private Double unitPrice;

    /**
     * The unit price of this item after applying tiered pricing policy.
     */
    private Double unitPriceFinal;

    /**
     * The original price of this item per sale unit in display currency of the user.
     */
    private Double dispUnitPrice;

    /**
     * The unit price of this item after applying tiered pricing policy in display currency of the user.
     */
    private Double dispUnitPriceFinal;

    /**
     * The currency code of the price (ISO 4217).
     */
    private String currencyCode;

    /**
     * The display currency code of the user (ISO 4217).
     */
    private String dispCurrencyCode;

    /**
     * User remarks of the order item.
     */
    private String remarks;

    /**
     * User description of the order item.
     */
    private String description;

    /**
     * The weight of the item that overrides the catalog weight. (Upcoming feature)
     */
    private Double weight;

    /**
     * Represents the item details.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        /**
         * Item's identification number in BL catalog.
         */
        private String no;
        /**
         * The name of the item.
         */
        private String name;
        /**
         * The type of the item. MINIFIG, PART, SET, BOOK, GEAR, CATALOG, INSTRUCTION, UNSORTED_LOT, ORIGINAL_BOX
         */
        private String type;
        /**
         * The main category of the item.
         */
        private Integer categoryId;
    }
}

