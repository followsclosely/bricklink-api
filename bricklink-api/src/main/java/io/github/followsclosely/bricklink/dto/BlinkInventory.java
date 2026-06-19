package io.github.followsclosely.bricklink.dto;

import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import tools.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "INVENTORY")
public class BlinkInventory {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ITEM")
    private List<BlinkInventoryItem> items;
}
