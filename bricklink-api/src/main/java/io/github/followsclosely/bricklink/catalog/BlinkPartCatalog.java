package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.dto.BlinkPart;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of BlinkPart objects.
 * Implementations of this interface allow streaming all available parts from the catalog.
 */
public interface BlinkPartCatalog {
    /**
     * Returns a stream of {@link BlinkPart} objects from the catalog.
     *
     * @return a {@code Stream} of {@code BlinkPart} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<BlinkPart> stream() throws IOException;
}

