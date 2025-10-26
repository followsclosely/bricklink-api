package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.dto.BlinkMinifigure;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of BlinkMinifigure objects.
 * Implementations of this interface allow streaming all available minifigures from the catalog.
 */
public interface BlinkMinifigureCatalog {
    /**
     * Returns a stream of {@link BlinkMinifigure} objects from the catalog.
     *
     * @return a {@code Stream} of {@code BlinkMinifigure} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<BlinkMinifigure> stream() throws IOException;
}

