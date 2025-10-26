package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.bricklink.dto.BlinkCategory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlinkCategoryCatalogLoaderTest {
    @Test
    void loadFromCatalog() throws IOException {
        BlinkCategoryCatalogLoader loader = new BlinkCategoryCatalogLoader();
        try (Stream<BlinkCategory> stream = loader.stream()) {
            Optional<BlinkCategory> category = stream.filter(c -> c.getId().equals(289L)).findFirst();
            category.ifPresentOrElse(
                    c -> assertEquals("Black Falcons", c.getName()),
                    () -> {
                        throw new AssertionError("Expected category not found in catalog");
                    }
            );
        }
    }
}
