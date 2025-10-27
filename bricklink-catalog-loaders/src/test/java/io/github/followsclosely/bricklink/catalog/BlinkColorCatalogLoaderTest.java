package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkColorDetails;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlinkColorCatalogLoaderTest {
    @Test
    void loadFromCatalog() throws IOException {
        BlinkColorCatalogLoader loader = new BlinkColorCatalogLoader();
        try (Stream<BlinkColorDetails> stream = loader.stream()) {
            Optional<BlinkColorDetails> first = stream.findFirst();

            assertTrue(first.isPresent());
            assertEquals(0L, first.get().getId());
            assertEquals("(Not Applicable)", first.get().getName());
        }
        //Stop after reading just the first entry from the stream.
        //streams are auto-closed by try-with-resources
    }
}