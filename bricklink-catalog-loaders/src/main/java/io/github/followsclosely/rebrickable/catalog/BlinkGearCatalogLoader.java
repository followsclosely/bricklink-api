package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.bricklink.catalog.BlinkGearCatalog;
import io.github.followsclosely.bricklink.dto.BlinkGear;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

@Slf4j
public class BlinkGearCatalogLoader extends AbstractCatalogLoader<BlinkGear> implements BlinkGearCatalog {

    public BlinkGearCatalogLoader() {
        super("../catalog/Gear.txt");
    }

    /**
     * The structure of Gear.txt:
     * Category ID / Category Name / Number / Name / Year Released / Weight (in Grams) / Dimensions
     *
     * @param record CSV record
     * @return Mapped BlinkGear object
     */
    @Override
    BlinkGear apply(CSVRecord record) {
        return BlinkGear.builder()
                .categoryId(parseLong(record, 0))
                .categoryName(record.get(1))
                .number(record.get(2))
                .name(record.get(3))
                .yearReleased(parseInt(record, 4))
                .weightGrams(parseDouble(record, 5))
                .dimensions(record.get(6))
                .build();
    }
}

