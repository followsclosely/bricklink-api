package io.github.followsclosely.bricklink.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceDetail {
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("qunatity") // Typo, to be deprecated
    private Integer qunatity;
    @JsonProperty("unit_price")
    private Double unitPrice;
    @JsonProperty("shipping_available")
    private String shippingAvailable;
    @JsonProperty("seller_country_code")
    private String sellerCountryCode;
    @JsonProperty("buyer_country_code")
    private String buyerCountryCode;
    @JsonProperty("date_ordered")
    private String dateOrdered;
}
