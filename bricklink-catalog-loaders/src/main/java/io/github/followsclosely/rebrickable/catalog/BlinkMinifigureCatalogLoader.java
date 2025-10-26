package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.bricklink.catalog.BlinkMinifigureCatalog;
import io.github.followsclosely.bricklink.dto.BlinkMinifigure;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

@Slf4j
public class BlinkMinifigureCatalogLoader extends AbstractCatalogLoader<BlinkMinifigure> implements BlinkMinifigureCatalog {

    public BlinkMinifigureCatalogLoader() {
        super("../catalog/Minifigures.txt");
    }

    /**
     * The structure of Minifigures.txt:
     * Category ID / Category Name / Number / Name / Year Released / Weight (in Grams)
     *
     * @param record CSV record
     * @return Mapped BlinkMinifigure object
     */
    @Override
    BlinkMinifigure apply(CSVRecord record) {
        return BlinkMinifigure.builder()
                .categoryId(parseLong(record, 0))
                .categoryName(record.get(1))
                .number(record.get(2))
                .name(record.get(3))
                .yearReleased(parseInt(record, 4))
                .weightGrams(parseDouble(record, 5))
                .build();
    }
}

