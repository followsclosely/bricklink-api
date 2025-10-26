package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.bricklink.catalog.BlinkCatalogCatalog;
import io.github.followsclosely.bricklink.dto.BlinkCatalog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

@Slf4j
public class BlinkCatalogCatalogLoader extends AbstractCatalogLoader<BlinkCatalog> implements BlinkCatalogCatalog {

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
    BlinkCatalog apply(CSVRecord record) {
        return BlinkCatalog.builder()
                .categoryId(parseLong(record, 0))
                .categoryName(record.get(1))
                .number(record.get(2))
                .name(record.get(3))
                .yearReleased(parseInt(record, 4))
                .weightGrams(parseDouble(record, 5))
                .build();
    }
}

