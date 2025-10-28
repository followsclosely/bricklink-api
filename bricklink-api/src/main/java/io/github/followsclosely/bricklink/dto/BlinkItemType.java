package io.github.followsclosely.bricklink.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration representing different types of items in the BrickLink catalog.<br>
 * Each item type has a unique identifier and a descriptive name.<br>
 * Provides a lookup method to retrieve an ItemType by its identifier.
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum BlinkItemType {

    SET("S", "Set"),
    PART("P", "Part"),
    MINIFIGURE("M", "Minifigure"),
    BOOK("B", "Book"),
    GEAR("G", "Gear"),
    CATALOG("C", "Catalog"),
    INSTRUCTION("I", "Instruction"),
    ORIGINAL_BOX("O", "Original Box"),
    UNSORTED_LOT("U", "Unsorted Lot"),
    UNKNOWN("?", "Unknown");

    private static final Map<String, BlinkItemType> ID_MAP;

    static {
        Map<String, BlinkItemType> map = new HashMap<>();
        for (BlinkItemType type : values()) {
            map.put(type.value, type);
        }
        ID_MAP = Collections.unmodifiableMap(map);
    }

    /**
     * The unique identifier for the item type (e.g., "S" for Set).
     */
    private final String value;
    /**
     * The descriptive name of the item type (e.g., "Set").
     */
    private final String displayName;

    /**
     * Returns the {@code ItemType} corresponding to the given identifier.<br>
     * If the identifier does not match any known item type, {@link #UNKNOWN} is returned.
     *
     * @param id the item type identifier (e.g., "S", "P")
     * @return the matching {@code ItemType}, or {@link #UNKNOWN} if not found
     */
    @JsonCreator
    public static BlinkItemType fromId(String id) {
        return ID_MAP.getOrDefault(id, UNKNOWN);
    }
}
