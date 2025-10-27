package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkOriginalBoxDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

@Slf4j
public class BlinkOriginalBoxCatalogLoader extends AbstractCatalogLoader<BlinkOriginalBoxDetails> implements BlinkOriginalBoxCatalog {

    public BlinkOriginalBoxCatalogLoader() {
        super("../catalog/Original Boxes.txt");
    }

    /**
     * The structure of Original Boxes.txt:
     * Category ID / Category Name / Number / Name / Year Released / Weight (in Grams) / Dimensions
     *
     * @param record CSV record
     * @return Mapped BlinkOriginalBox object
     */
    @Override
    BlinkOriginalBoxDetails apply(CSVRecord record) {
        return BlinkOriginalBoxDetails.builder()
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

