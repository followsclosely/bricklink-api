package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.bricklink.catalog.BlinkBookCatalog;
import io.github.followsclosely.bricklink.dto.BlinkBook;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

@Slf4j
public class BlinkBookCatalogLoader extends AbstractCatalogLoader<BlinkBook> implements BlinkBookCatalog {

    public BlinkBookCatalogLoader() {
        super("../catalog/Books.txt");
    }

    /**
     * The structure of Books.txt:
     * Category ID / Category Name / Number / Name / Year Released / Weight (in Grams) / Dimensions
     *
     * @param record CSV record
     * @return Mapped BlinkBook object
     */
    @Override
    BlinkBook apply(CSVRecord record) {
        return BlinkBook.builder()
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

