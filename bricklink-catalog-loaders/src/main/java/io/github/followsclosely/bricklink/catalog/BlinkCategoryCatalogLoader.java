package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkCategoryDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

@Slf4j
public class BlinkCategoryCatalogLoader extends AbstractCatalogLoader<BlinkCategoryDetails> {

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
    BlinkCategoryDetails apply(CSVRecord record) {
        return BlinkCategoryDetails.builder()
                .id(parseLong(record, 0))
                .name(record.get(1))
                .build();
    }
}

