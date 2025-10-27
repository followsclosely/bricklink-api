package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkCatalogDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

@Slf4j
public class BlinkCatalogCatalogLoader extends AbstractCatalogLoader<BlinkCatalogDetails> {

    public BlinkCatalogCatalogLoader() {
        super("../catalog/Catalogs.txt");
    }

    /**
     * The structure of Catalogs.txt:
     * Category ID / Category Name / Number / Name / Year Released / Weight (in Grams)
     *
     * @param record CSV record
     * @return Mapped BlinkCatalog object
     */
    @Override
    BlinkCatalogDetails apply(CSVRecord record) {
        return BlinkCatalogDetails.builder()
                .categoryId(parseLong(record, 0))
                .categoryName(record.get(1))
                .number(record.get(2))
                .name(record.get(3))
                .yearReleased(parseInt(record, 4))
                .weightGrams(parseDouble(record, 5))
                .build();
    }
}

