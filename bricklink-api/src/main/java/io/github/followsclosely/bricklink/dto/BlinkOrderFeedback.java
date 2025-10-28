package io.github.followsclosely.bricklink.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Represents feedback for a Bricklink order.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlinkOrderFeedback {
    /**
     * An identification of the feedback.
     */
    private Integer feedbackId;

    /**
     * The ID of the order associated with the feedback.
     */
    private Integer orderId;

    /**
     * The username of who posts this feedback.
     */
    private String from;

    /**
     * The username of who receives this feedback.
     */
    private String to;

    /**
     * The time the feedback was posted.
     */
    private Timestamp dateRated;

    /**
     * The rating for a transaction (scale 0 to 2). 0: Praise, 1: Neutral, 2: Complaint
     */
    private Integer rating;

    /**
     * Indicates whether the feedback is written for a seller or a buyer. S: Seller, B: Buyer
     */
    private String ratingOfBs;

    /**
     * A comment associated with the feedback.
     */
    private String comment;

    /**
     * A reply for this feedback.
     */
    private String reply;
}

