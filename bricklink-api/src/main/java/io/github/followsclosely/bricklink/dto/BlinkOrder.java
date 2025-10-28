package io.github.followsclosely.bricklink.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlinkOrder {
    @JsonProperty("order_id")
    private String orderId;
    @JsonProperty("date_ordered")
    private String dateOrdered;
    @JsonProperty("date_status_changed")
    private String dateStatusChanged;
    @JsonProperty("seller_name")
    private String sellerName;
    @JsonProperty("store_name")
    private String storeName;
    @JsonProperty("buyer_name")
    private String buyerName;
    @JsonProperty("buyer_email")
    private String buyerEmail;
    @JsonProperty("buyer_order_count")
    private Integer buyerOrderCount;
    @JsonProperty("require_insurance")
    private Boolean requireInsurance;
    @JsonProperty("status")
    private String status;
    @JsonProperty("is_invoiced")
    private Boolean isInvoiced;
    @JsonProperty("is_filed")
    private Boolean isFiled;
    @JsonProperty("drive_thru_sent")
    private Boolean driveThruSent;
    @JsonProperty("salesTax_collected_by_bl")
    private Boolean salesTaxCollectedByBl;
    @JsonProperty("remarks")
    private String remarks;
    @JsonProperty("total_count")
    private Integer totalCount;
    @JsonProperty("unique_count")
    private Integer uniqueCount;
    @JsonProperty("total_weight")
    private Double totalWeight;
    @JsonProperty("payment")
    private Payment payment;
    @JsonProperty("shipping")
    private Shipping shipping;
    @JsonProperty("cost")
    private Cost cost;
    @JsonProperty("disp_cost")
    private DispCost dispCost;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payment {
        @JsonProperty("method")
        private String method;
        @JsonProperty("currency_code")
        private String currencyCode;
        @JsonProperty("date_paid")
        private String datePaid;
        @JsonProperty("status")
        private String status;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Shipping {
        @JsonProperty("method")
        private String method;
        @JsonProperty("method_id")
        private String methodId;
        @JsonProperty("tracking_no")
        private String trackingNo;
        @JsonProperty("tracking_link")
        private String trackingLink;
        @JsonProperty("date_shipped")
        private String dateShipped;
        @JsonProperty("address")
        private Address address;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Address {
        @JsonProperty("name")
        private Name name;
        @JsonProperty("full")
        private String full;
        @JsonProperty("address1")
        private String address1;
        @JsonProperty("address2")
        private String address2;
        @JsonProperty("country_code")
        private String countryCode;
        @JsonProperty("city")
        private String city;
        @JsonProperty("state")
        private String state;
        @JsonProperty("postal_code")
        private String postalCode;
        @JsonProperty("phone_number")
        private String phoneNumber;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Name {
        @JsonProperty("full")
        private String full;
        @JsonProperty("first")
        private String first;
        @JsonProperty("last")
        private String last;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Cost {
        @JsonProperty("currency_code")
        private String currencyCode;
        @JsonProperty("subtotal")
        private Double subtotal;
        @JsonProperty("grand_total")
        private Double grandTotal;
        @JsonProperty("salesTax_collected_by_BL")
        private Double salesTaxCollectedByBL;
        @JsonProperty("final_total")
        private Double finalTotal;
        @JsonProperty("etc1")
        private Double etc1;
        @JsonProperty("etc2")
        private Double etc2;
        @JsonProperty("insurance")
        private Double insurance;
        @JsonProperty("shipping")
        private Double shipping;
        @JsonProperty("credit")
        private Double credit;
        @JsonProperty("coupon")
        private Double coupon;
        @JsonProperty("vat_rate")
        private Double vatRate;
        @JsonProperty("vat_amount")
        private Double vatAmount;
        @JsonProperty("disp_currency_code")
        private String displayCurrencyCode;
        @JsonProperty("disp_subtotal")
        private Double displaySubtotal;
        @JsonProperty("disp_grand_total")
        private Double displayGrandTotal;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DispCost {
        @JsonProperty("currency_code")
        private String currencyCode;
        @JsonProperty("subtotal")
        private Double subtotal;
        @JsonProperty("grand_total")
        private Double grandTotal;
        @JsonProperty("etc1")
        private Double etc1;
        @JsonProperty("etc2")
        private Double etc2;
        @JsonProperty("insurance")
        private Double insurance;
        @JsonProperty("shipping")
        private Double shipping;
        @JsonProperty("credit")
        private Double credit;
        @JsonProperty("coupon")
        private Double coupon;
        @JsonProperty("vat_rate")
        private Double vatRate;
        @JsonProperty("vat_amount")
        private Double vatAmount;
    }

    /**
     * Represents feedback for a Bricklink order.
     */
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Feedback {
        @JsonProperty("feedback_id")
        private Integer feedbackId;
        @JsonProperty("order_id")
        private Integer orderId;
        @JsonProperty("from")
        private String from;
        @JsonProperty("to")
        private String to;
        @JsonProperty("date_rated")
        private Timestamp dateRated;
        @JsonProperty("rating")
        private Integer rating;
        @JsonProperty("rating_of_bs")
        private String ratingOfBs;
        @JsonProperty("comment")
        private String comment;
        @JsonProperty("reply")
        private String reply;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OrderItem {
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
        @JsonIgnoreProperties(ignoreUnknown = true)
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

    /**
     * Represents a message associated with a Bricklink order.
     */
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ToString(of = {"from", "to", "dateSent", "subject"})
    public static class Message {
        /**
         * The subject of the message.
         */
        private String subject;

        /**
         * The contents of the message.
         */
        private String body;

        /**
         * The username of who sends the message.
         */
        private String from;

        /**
         * The username of who receives the message.
         */
        private String to;

        /**
         * The time the message was sent.
         */
        private Timestamp dateSent;
    }
}
