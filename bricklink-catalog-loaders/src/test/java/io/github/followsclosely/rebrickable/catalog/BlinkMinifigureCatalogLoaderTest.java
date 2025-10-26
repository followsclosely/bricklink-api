package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.bricklink.dto.BlinkMinifigure;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlinkMinifigureCatalogLoaderTest {
    @Test
    void loadFromCatalog() throws IOException {
        BlinkMinifigureCatalogLoader loader = new BlinkMinifigureCatalogLoader();
        Optional<BlinkMinifigure> minifig = loader.stream().filter(part -> "cas019".equals(part.getNumber())).findFirst();

        assertTrue(minifig.isPresent());
        assertEquals("Majisto Wizard", minifig.get().getName());
    }
}


