package io.github.followsclosely.bricklink.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
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
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
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
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
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
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
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
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
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
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
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
}
