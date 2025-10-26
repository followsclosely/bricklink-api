package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.dto.BlinkElement;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of BlinkElement objects.
 * Implementations of this interface allow streaming all available element codes from the catalog.
 */
public interface BlinkElementCatalog {
    /**
     * Returns a stream of {@link BlinkElement} objects from the catalog.
     *
     * @return a {@code Stream} of {@code BlinkElement} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<BlinkElement> stream() throws IOException;
}

