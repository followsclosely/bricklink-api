package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.dto.BlinkCategory;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of BlinkCategory objects.
 * Implementations of this interface allow streaming all available categories from the catalog.
 */
public interface BlinkCategoryCatalog {
    /**
     * Returns a stream of {@link BlinkCategory} objects from the catalog.
     *
     * @return a {@code Stream} of {@code BlinkCategory} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<BlinkCategory> stream() throws IOException;
}

