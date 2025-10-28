package io.github.followsclosely.bricklink.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlinkPriceGuide {
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

