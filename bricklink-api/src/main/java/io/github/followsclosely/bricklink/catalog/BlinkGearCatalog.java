package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.dto.BlinkGear;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of BlinkGear objects.
 * Implementations of this interface allow streaming all available gear items from the catalog.
 */
public interface BlinkGearCatalog {
    /**
     * Returns a stream of {@link BlinkGear} objects from the catalog.
     *
     * @return a {@code Stream} of {@code BlinkGear} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<BlinkGear> stream() throws IOException;
}

