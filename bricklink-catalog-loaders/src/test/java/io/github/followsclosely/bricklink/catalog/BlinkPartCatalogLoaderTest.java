package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkPartDetails;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlinkPartCatalogLoaderTest {
    @Test
    void loadFromCatalog() throws IOException {
        BlinkPartCatalogLoader loader = new BlinkPartCatalogLoader();
        try (Stream<BlinkPartDetails> stream = loader.stream()) {
            stream.filter(part -> "3846p44".equals(part.getNumber()))
                    .findFirst()
                    .ifPresentOrElse(part -> assertEquals("Minifigure, Shield Triangular Short with Wolfpack Pattern (Undetermined Type)", part.getName()), () -> {
                        throw new AssertionError("Expected part not found in catalog");
                    });
        }
    }
}


