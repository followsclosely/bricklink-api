package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.bricklink.catalog.BlinkPartCatalog;
import io.github.followsclosely.bricklink.dto.BlinkPart;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

@Slf4j
public class BlinkPartCatalogLoader extends AbstractCatalogLoader<BlinkPart> implements BlinkPartCatalog {

    public BlinkPartCatalogLoader() {
        super("../catalog/Parts.txt");
    }

    /**
     * The structure of Parts.txt:
     * Category ID / Category Name / Number / Name / Alternate Item Number / Weight (in Grams) / Dimensions
     *
     * @param record CSV record
     * @return Mapped BlinkPart object
     */
    @Override
    BlinkPart apply(CSVRecord record) {
        return BlinkPart.builder()
                .categoryId(parseLong(record, 0))
                .categoryName(record.get(1))
                .number(record.get(2))
                .name(record.get(3))
                .alternateItemNumber(record.get(4))
                .weightGrams(parseDouble(record, 5))
                .dimensions(record.get(6))
                .build();
    }
}

