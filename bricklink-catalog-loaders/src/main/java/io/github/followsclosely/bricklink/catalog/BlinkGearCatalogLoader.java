package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkGearDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

/**
 * Loads and maps data from the BrickLink Gear catalog file (Gear.txt).
 * <p>
 * The file structure is: Category ID / Category Name / Number / Name / Year Released / Weight (in Grams) / Dimensions
 * <p>
 * Each record is mapped to a {@link io.github.followsclosely.bricklink.catalog.dto.BlinkGearDetails} DTO.
 * <p>
 * Usage: Instantiate and call {@code stream()} to process records as domain objects.
 */
@Slf4j
public class BlinkGearCatalogLoader extends AbstractCatalogLoader<BlinkGearDetails> {

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
    BlinkGearDetails apply(CSVRecord record) {
        return BlinkGearDetails.builder()
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
