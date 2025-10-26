package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.dto.BlinkBook;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of BlinkBook objects.
 * Implementations of this interface allow streaming all available books from the catalog.
 */
public interface BlinkBookCatalog {
    /**
     * Returns a stream of {@link BlinkBook} objects from the catalog.
     *
     * @return a {@code Stream} of {@code BlinkBook} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<BlinkBook> stream() throws IOException;
}

