package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkInstructionDetails;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlinkInstructionCatalogLoaderTest {
    @Test
    void loadFromCatalog() throws IOException {
        BlinkInstructionCatalogLoader loader = new BlinkInstructionCatalogLoader();
        try (Stream<BlinkInstructionDetails> stream = loader.stream()) {
            Optional<BlinkInstructionDetails> instruction = stream.filter(i -> "10350-1".equals(i.getNumber())).findFirst();
            instruction.ifPresentOrElse(
                    i -> assertEquals("Tudor Corner", i.getName()),
                    () -> {
                        throw new AssertionError("Expected instruction not found in catalog");
                    }
            );
        }
    }
}
