package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.bricklink.catalog.BlinkElementCatalog;
import io.github.followsclosely.bricklink.dto.BlinkElement;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

@Slf4j
public class BlinkElementCatalogLoader extends AbstractCatalogLoader<BlinkElement> implements BlinkElementCatalog {

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
    BlinkElement apply(CSVRecord record) {
        return BlinkElement.builder()
                .itemNo(record.get(0))
                .color(record.get(1))
                .code(record.get(2))
                .build();
    }
}

