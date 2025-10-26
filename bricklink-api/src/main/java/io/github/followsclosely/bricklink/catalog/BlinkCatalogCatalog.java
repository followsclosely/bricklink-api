package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.dto.BlinkCatalog;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of BlinkCatalog objects.
 * Implementations of this interface allow streaming all available catalog items from the catalog.
 */
public interface BlinkCatalogCatalog {
    /**
     * Returns a stream of {@link BlinkCatalog} objects from the catalog.
     *
     * @return a {@code Stream} of {@code BlinkCatalog} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<BlinkCatalog> stream() throws IOException;
}

