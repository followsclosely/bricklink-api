package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkPartDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

/**
 * Loads and maps data from the BrickLink Parts catalog file (Parts.txt).
 * <p>
 * The file structure is: Category ID / Category Name / Number / Name / Alternate Item Number / Weight (in Grams) / Dimensions
 * <p>
 * Each record is mapped to a {@link io.github.followsclosely.bricklink.catalog.dto.BlinkPartDetails} DTO.
 * <p>
 * Usage: Instantiate and call {@code stream()} to process records as domain objects.
 */
@Slf4j
public class BlinkPartCatalogLoader extends AbstractCatalogLoader<BlinkPartDetails> {

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
    BlinkPartDetails apply(CSVRecord record) {
        return BlinkPartDetails.builder()
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
