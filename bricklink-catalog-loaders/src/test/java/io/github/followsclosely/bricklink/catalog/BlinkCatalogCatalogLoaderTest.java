package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkCatalogDetails;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlinkCatalogCatalogLoaderTest {
    @Test
    void loadFromCatalog() throws IOException {
        BlinkCatalogCatalogLoader loader = new BlinkCatalogCatalogLoader();
        try (Stream<BlinkCatalogDetails> stream = loader.stream()) {
            Optional<BlinkCatalogDetails> catalog = stream.filter(c -> "m96cas".equals(c.getNumber())).findFirst();
            catalog.ifPresentOrElse(
                    c -> assertEquals("1996 Mini Castle North America (4.103.770-NA)", c.getName()),
                    () -> {
                        throw new AssertionError("Expected catalog not found in catalog");
                    }
            );
        }
    }
}
