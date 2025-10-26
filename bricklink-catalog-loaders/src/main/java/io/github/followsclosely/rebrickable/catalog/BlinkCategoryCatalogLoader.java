package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.bricklink.catalog.BlinkCategoryCatalog;
import io.github.followsclosely.bricklink.dto.BlinkCategory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

@Slf4j
public class BlinkCategoryCatalogLoader extends AbstractCatalogLoader<BlinkCategory> implements BlinkCategoryCatalog {

    public BlinkCategoryCatalogLoader() {
        super("../catalog/categories.txt");
    }

    /**
     * The structure of categories.txt:
     * Category ID / Category Name
     *
     * @param record CSV record
     * @return Mapped BlinkCategory object
     */
    @Override
    BlinkCategory apply(CSVRecord record) {
        return BlinkCategory.builder()
                .id(parseLong(record, 0))
                .name(record.get(1))
                .build();
    }
}

