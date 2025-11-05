package io.github.followsclosely.bricklink;

import io.github.followsclosely.bricklink.dto.BlinkCategory;
import io.github.followsclosely.bricklink.dto.BlinkResponse;

import java.util.List;

/**
 * Client interface for interacting with Bricklink category data.
 */
public interface BlinkCategoryClient {

    /**
     * Retrieves details of a specific category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return A BlinkResponse containing the category details.
     * @see <a href="https://www.bricklink.com/v3/api.page?page=get-category-list">Bricklink API Guide - Get Category List</a>
     */
    BlinkResponse<BlinkCategory> getCategory(Long id);

    /**
     * Retrieves a list of all category available in the Bricklink database.
     *
     * @return A BlinkResponse containing a list of category.
     * @see <a href="https://www.bricklink.com/v3/api.page?page=get-category-list">Bricklink API Guide - Get Category List</a>
     */
    BlinkResponse<List<BlinkCategory>> getCategories();
}
