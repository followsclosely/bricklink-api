package io.github.followsclosely.bricklink;

import io.github.followsclosely.bricklink.dto.BlinkOrder;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Client interface for interacting with Bricklink orders.
 */
public interface BlinkOrderClient {

    /**
     * Retrieves a specific order by its ID.
     *
     * @param id The ID of the order to retrieve.
     * @return A BlinkResponse containing the order details.
     *
     * @see <a href="https://www.bricklink.com/v3/api.page?page=get-order">Bricklink API Guide - Get Order</a>
     */
    BlinkResponse<BlinkOrder> getOrder(Integer id);

    /**
     * Retrieves a list of orders based on the provided query parameters.
     *
     * @param query The query parameters for filtering orders.
     * @return A BlinkResponse containing a list of orders matching the query.
     *
     * @see <a href="https://www.bricklink.com/v3/api.page?page=get-orders">Bricklink API Guide - Get Orders</a>
     */
    BlinkResponse<List<BlinkOrder>> getOrders(Query query);

    @Data
    @Builder
    class Query {
        /**
         * The direction of the order to get. Acceptable values are:
         * - "out": Gets placed orders.
         * - "in": Gets received orders. (default)
         */
        @Builder.Default
        private Direction direction = Direction.in;
        /**
         * The status of the order to include or exclude.
         * - If you don't specify this value, this method retrieves orders in any status.
         * - You can pass a comma-separated string to specify multiple status to include/exclude.
         * - You can add a minus(-) sign to specify a status to exclude
         */
        private String status;
        /**
         * Indicates whether the result retries filed or un-filed orders. Acceptable values are:
         * - "true"
         * - "false": (default)
         */
        private Boolean filed;

        public enum Direction {
            in, out
        }
    }
}
