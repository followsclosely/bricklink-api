package io.github.followsclosely.bricklink.catalog;

import io.github.followsclosely.bricklink.dto.BlinkInstruction;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of BlinkInstruction objects.
 * Implementations of this interface allow streaming all available instruction items from the catalog.
 */
public interface BlinkInstructionCatalog {
    /**
     * Returns a stream of {@link BlinkInstruction} objects from the catalog.
     *
     * @return a {@code Stream} of {@code BlinkInstruction} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<BlinkInstruction> stream() throws IOException;
}

