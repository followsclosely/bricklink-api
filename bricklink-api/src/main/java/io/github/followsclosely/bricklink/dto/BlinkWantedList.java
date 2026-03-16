package io.github.followsclosely.bricklink.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JacksonXmlRootElement(localName = "INVENTORY")
public class BlinkWantedList {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ITEM")
    private final List<BlinkWantedItem> items;
}
