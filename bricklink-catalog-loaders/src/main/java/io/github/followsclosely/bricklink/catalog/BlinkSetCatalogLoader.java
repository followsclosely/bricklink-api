package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkSetDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

/**
 * Loads and maps data from the BrickLink Sets catalog file (Sets.txt).
 * <p>
 * The file structure is: Category ID	Category Name	Number	Name	Year Released	Weight (in Grams)	Dimensions
 * <p>
 * Each record is mapped to a {@link io.github.followsclosely.bricklink.catalog.dto.BlinkSetDetails} DTO.
 * <p>
 * Usage: Instantiate and call {@code stream()} to process records as domain objects.
 */
@Slf4j
public class BlinkSetCatalogLoader extends AbstractCatalogLoader<BlinkSetDetails> {

    public BlinkSetCatalogLoader() {
        super("Sets.txt");
    }

    /**
     * The structure of Sets.txt:
     * Category ID	Category Name	Number	Name	Year Released	Weight (in Grams)	Dimensions
     *
     * @param record CSV record
     * @return Mapped BlinkSetDetails object
     */
    @Override
    BlinkSetDetails apply(CSVRecord record) {
        return BlinkSetDetails.builder()
                .categoryId(parseLong(record, 0))
                .categoryName(record.get(1))
                .number(record.get(2))
                .name(record.get(3))
                .yearReleased(parseInt(record, 4))
                .weightGrams(parseDouble(record, 5))
                .dimensions(parseString(record, 6))
                .build();
    }
}

