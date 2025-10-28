package io.github.followsclosely.bricklink;

import io.github.followsclosely.bricklink.dto.BlinkOrder;
import io.github.followsclosely.bricklink.dto.BlinkOrderFeedback;
import io.github.followsclosely.bricklink.dto.BlinkOrderItem;
import io.github.followsclosely.bricklink.dto.BlinkOrderMessage;
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

    /**
     * Retrieves the items associated with a specific order.
     * <br><br>
     *
     * <b>Note:</b> The BrickLink API /orders/{order_id}/items returns an array of arrays because the items in an order can be
     * separated into different batches or "matches". This structure accounts for scenarios where new items are
     * added to an existing order, or when the initial order itself consists of multiple batches.
     *
     * For example, if you add an item to an order that is still open, the new items will be in a separate batch.
     * Each inner array represents one such batch, with the objects inside detailing the specific items within that batch.
     *
     * @param id The ID of the order whose items are to be retrieved.
     * @return A BlinkResponse containing a list of list order items.
     *
     * @see <a href="https://www.bricklink.com/v3/api.page?page=get-order-items">Bricklink API Guide - Get Order Items</a>
     */
    BlinkResponse<List<List<BlinkOrderItem>>> getOrderItems(Integer id);

    /**
     * Retrieves the messages associated with a specific order.
     *
     * @param id The ID of the order whose messages are to be retrieved.
     * @return A BlinkResponse containing a list of order messages.
     *
     * @see <a href="https://www.bricklink.com/v3/api.page?page=get-order-messages">Bricklink API Guide - Get Order Messages</a>
     */
    BlinkResponse<List<BlinkOrderMessage>> getOrderMessages(Integer id);

    /**
     * Retrieves the feedback associated with a specific order.
     *
     * @param orderId The ID of the order whose feedback is to be retrieved.
     * @return A BlinkResponse containing a list of feedback for the order.
     *
     * @see <a href="https://www.bricklink.com/v3/api.page?page=get-order-feedback">Bricklink API Guide - Get Order Feedback</a>
     */
    BlinkResponse<List<BlinkOrderFeedback>> getOrderFeedback(Integer orderId);
}
