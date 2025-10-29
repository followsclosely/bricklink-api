package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkColorDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

/**
 * Loads and maps data from the BrickLink colors catalog file (colors.txt).
 * <p>
 * The file structure is: Color ID / Color Name / RGB / Type / Parts / In Sets / Wanted / For Sale / Year From / Year To
 * <p>
 * Each record is mapped to a {@link io.github.followsclosely.bricklink.catalog.dto.BlinkColorDetails} DTO.
 * <p>
 * Usage: Instantiate and call {@code stream()} to process records as domain objects.
 */
@Slf4j
public class BlinkColorCatalogLoader extends AbstractCatalogLoader<BlinkColorDetails> {

    public BlinkColorCatalogLoader() {
        super("../catalog/colors.txt");
    }

    /**
     * The structure of colors.txt:
     * Color ID	/ Color Name / RGB / Type / Parts / In Sets / Wanted / For Sale / Year From / Year To
     *
     * @param record CSV record
     * @return Mapped BlinkColor object
     */
    public BlinkColorDetails apply(CSVRecord record) {
        return BlinkColorDetails.builder()
                .id(parseLong(record, 0))
                .name(record.get(1))
                .rgb(record.get(2))
                .type(record.get(3))
                .parts(parseInt(record, 4))
                .inSets(parseInt(record, 5))
                .wanted(parseInt(record, 6))
                .forSale(parseInt(record, 7))
                .yearFrom(parseInt(record, 8))
                .yearTo(parseInt(record, 9))
                .build();
    }
}
