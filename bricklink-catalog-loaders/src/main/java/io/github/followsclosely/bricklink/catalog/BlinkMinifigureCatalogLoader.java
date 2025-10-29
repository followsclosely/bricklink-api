package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkMinifigureDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

/**
 * Loads and maps data from the BrickLink Minifigures catalog file (Minifigures.txt).
 * <p>
 * The file structure is: Category ID / Category Name / Number / Name / Year Released / Weight (in Grams)
 * <p>
 * Each record is mapped to a {@link io.github.followsclosely.bricklink.catalog.dto.BlinkMinifigureDetails} DTO.
 * <p>
 * Usage: Instantiate and call {@code stream()} to process records as domain objects.
 */
@Slf4j
public class BlinkMinifigureCatalogLoader extends AbstractCatalogLoader<BlinkMinifigureDetails>  {

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
    BlinkMinifigureDetails apply(CSVRecord record) {
        return BlinkMinifigureDetails.builder()
                .categoryId(parseLong(record, 0))
                .categoryName(record.get(1))
                .number(record.get(2))
                .name(record.get(3))
                .yearReleased(parseInt(record, 4))
                .weightGrams(parseDouble(record, 5))
                .build();
    }
}
