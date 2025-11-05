package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.catalog.dto.BlinkSetDetails;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlinkSetCatalogLoaderTest {
    @Test
    void loadFromCatalog() throws IOException {
        BlinkSetCatalogLoader loader = new BlinkSetCatalogLoader();
        Optional<BlinkSetDetails> set = loader.stream().filter(part -> "10350-1".equals(part.getNumber())).findFirst();

        assertTrue(set.isPresent());
        assertEquals("Tudor Corner", set.get().getName());
    }
}


