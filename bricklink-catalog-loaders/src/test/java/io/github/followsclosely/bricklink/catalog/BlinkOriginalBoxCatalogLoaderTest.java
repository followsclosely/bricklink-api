package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkOriginalBoxDetails;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlinkOriginalBoxCatalogLoaderTest {
    @Test
    void loadFromCatalog() throws IOException {
        BlinkOriginalBoxCatalogLoader loader = new BlinkOriginalBoxCatalogLoader();
        try (Stream<BlinkOriginalBoxDetails> stream = loader.stream()) {
            Optional<BlinkOriginalBoxDetails> box = stream.filter(b -> "10350-1".equals(b.getNumber())).findFirst();
            box.ifPresentOrElse(
                    b -> assertEquals("Tudor Corner", b.getName()),
                    () -> {
                        throw new AssertionError("Expected original box not found in catalog");
                    }
            );
        }
    }
}
