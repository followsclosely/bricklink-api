package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.bricklink.catalog.BlinkInstructionCatalog;
import io.github.followsclosely.bricklink.dto.BlinkInstruction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

@Slf4j
public class BlinkInstructionCatalogLoader extends AbstractCatalogLoader<BlinkInstruction> implements BlinkInstructionCatalog {

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
    BlinkInstruction apply(CSVRecord record) {
        return BlinkInstruction.builder()
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

