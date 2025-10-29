package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkElementDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

/**
 * Loads and maps data from the BrickLink element codes catalog file (codes.txt).
 * <p>
 * The file structure is: Item No / Color / Code
 * <p>
 * Each record is mapped to a {@link io.github.followsclosely.bricklink.catalog.dto.BlinkElementDetails} DTO.
 * <p>
 * Usage: Instantiate and call {@code stream()} to process records as domain objects.
 */
@Slf4j
public class BlinkElementCatalogLoader extends AbstractCatalogLoader<BlinkElementDetails> {

    public BlinkElementCatalogLoader() {
        super("../catalog/codes.txt");
    }

    /**
     * The structure of codes.txt:
     * Item No / Color / Code
     *
     * @param record CSV record
     * @return Mapped BlinkElement object
     */
    @Override
    BlinkElementDetails apply(CSVRecord record) {
        return BlinkElementDetails.builder()
                .itemNo(record.get(0))
                .color(record.get(1))
                .code(record.get(2))
                .build();
    }
}
