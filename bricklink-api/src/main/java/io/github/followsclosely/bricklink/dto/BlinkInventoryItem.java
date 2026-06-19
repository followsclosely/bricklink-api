package io.github.followsclosely.bricklink.dto;

import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class BlinkInventoryItem {

    @JacksonXmlProperty(localName = "ITEMTYPE")
    private String itemType;

    @JacksonXmlProperty(localName = "ITEMID")
    private String itemId;

    @JacksonXmlProperty(localName = "COLOR")
    private Integer color;

    @JacksonXmlProperty(localName = "QTY")
    private Integer qty;

    @JacksonXmlProperty(localName = "CONDITION")
    private String condition;

    @JacksonXmlProperty(localName = "SUBCONDITION")
    private String subCondition;
}
