package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkBookDetails;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlinkBookCatalogLoaderTest {
    @Test
    void loadFromCatalog() throws IOException {
        BlinkBookCatalogLoader loader = new BlinkBookCatalogLoader();
        try (Stream<BlinkBookDetails> stream = loader.stream()) {
            Optional<BlinkBookDetails> book = stream.filter(b -> "250".equals(b.getNumber())).findFirst();
            book.ifPresentOrElse(
                    b -> assertEquals("Idea Book 250", b.getName()),
                    () -> {
                        throw new AssertionError("Expected book not found in catalog");
                    }
            );
        }
    }
}


