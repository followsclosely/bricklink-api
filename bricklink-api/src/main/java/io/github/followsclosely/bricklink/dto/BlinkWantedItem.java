package io.github.followsclosely.bricklink.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlinkWantedItem {

    @JacksonXmlProperty(localName = "ITEMTYPE")
    private final String itemType;

    @JacksonXmlProperty(localName = "ITEMID")
    private final String itemId;

    @JacksonXmlProperty(localName = "COLOR")
    private final Integer color;

    @JacksonXmlProperty(localName = "MAXPRICE")
    private final String maxPrice;

    @JacksonXmlProperty(localName = "MINQTY")
    private final int minQty;

    @JacksonXmlProperty(localName = "CONDITION")
    private final String condition;

    @JacksonXmlProperty(localName = "REMARKS")
    private final String remarks;

    @JacksonXmlProperty(localName = "NOTIFY")
    private final String notify;
}
