package io.github.followsclosely.bricklink;

import io.github.followsclosely.bricklink.dto.BlinkColor;
import io.github.followsclosely.bricklink.dto.BlinkResponse;

import java.util.List;

/**
 * Client interface for interacting with Bricklink color data.
 */
public interface BlinkColorClient {

    /**
     * Retrieves details of a specific color by its ID.
     *
     * @param id The ID of the color to retrieve.
     * @return A BlinkResponse containing the color details.
     * @see <a href="https://www.bricklink.com/v3/api.page?page=get-color">Bricklink API Guide - Get Color</a>
     */
    BlinkResponse<BlinkColor> getColor(Integer id);

    /**
     * Retrieves a list of all colors available in the Bricklink database.
     *
     * @return A BlinkResponse containing a list of colors.
     * @see <a href="https://www.bricklink.com/v3/api.page?page=get-color-list">Bricklink API Guide - Get Color List</a>
     */
    BlinkResponse<List<BlinkColor>> getColors();
}
