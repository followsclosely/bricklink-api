package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkElementDetails;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlinkElementCatalogLoaderTest {
    @Test
    void loadFromCatalog() throws IOException {
        BlinkElementCatalogLoader loader = new BlinkElementCatalogLoader();
        try (Stream<BlinkElementDetails> stream = loader.stream()) {
            Optional<BlinkElementDetails> element = stream.filter(e -> "3023".equals(e.getItemNo()) && "Black".equals(e.getColor())).findFirst();
            element.ifPresentOrElse(
                    e -> assertEquals("302326", e.getCode()),
                    () -> {
                        throw new AssertionError("Expected element not found in catalog");
                    }
            );
        }
    }
}
