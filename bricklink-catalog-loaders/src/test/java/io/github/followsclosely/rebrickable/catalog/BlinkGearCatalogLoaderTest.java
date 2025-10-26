package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.bricklink.dto.BlinkGear;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlinkGearCatalogLoaderTest {
    @Test
    void loadFromCatalog() throws IOException {
        BlinkGearCatalogLoader loader = new BlinkGearCatalogLoader();
        try (Stream<BlinkGear> stream = loader.stream()) {
            Optional<BlinkGear> gear = stream.filter(g -> "22408c01".equals(g.getNumber())).findFirst();
            gear.ifPresentOrElse(
                    g -> assertEquals("Nexo Knights Shield Key Chain", g.getName()),
                    () -> {
                        throw new AssertionError("Expected gear not found in catalog");
                    }
            );
        }
    }
}
