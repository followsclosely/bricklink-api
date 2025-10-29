package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkInstructionDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

/**
 * Loads and maps data from the BrickLink Instructions catalog file (Instructions.txt).
 * <p>
 * The file structure is: Category ID / Category Name / Number / Name / Year Released / Weight (in Grams) / Dimensions
 * <p>
 * Each record is mapped to a {@link io.github.followsclosely.bricklink.catalog.dto.BlinkInstructionDetails} DTO.
 * <p>
 * Usage: Instantiate and call {@code stream()} to process records as domain objects.
 */
@Slf4j
public class BlinkInstructionCatalogLoader extends AbstractCatalogLoader<BlinkInstructionDetails>  {

    public BlinkInstructionCatalogLoader() {
        super("../catalog/Instructions.txt");
    }

    /**
     * The structure of Instructions.txt:
     * Category ID / Category Name / Number / Name / Year Released / Weight (in Grams) / Dimensions
     *
     * @param record CSV record
     * @return Mapped BlinkInstruction object
     */
    @Override
    BlinkInstructionDetails apply(CSVRecord record) {
        return BlinkInstructionDetails.builder()
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
