package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.dto.BlinkOriginalBox;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of BlinkOriginalBox objects.
 * Implementations of this interface allow streaming all available original box items from the catalog.
 */
public interface BlinkOriginalBoxCatalog {
    /**
     * Returns a stream of {@link BlinkOriginalBox} objects from the catalog.
     *
     * @return a {@code Stream} of {@code BlinkOriginalBox} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<BlinkOriginalBox> stream() throws IOException;
}

