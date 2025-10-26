package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.dto.BlinkColor;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of BlinkColor objects.
 * Implementations of this interface allow streaming all available colors from the catalog.
 */
public interface BlinkColorCatalog {
    /**
     * Returns a stream of {@link BlinkColor} objects from the catalog.
     *
     * @return a {@code Stream} of {@code BlinkColor} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<BlinkColor> stream() throws IOException;
}
