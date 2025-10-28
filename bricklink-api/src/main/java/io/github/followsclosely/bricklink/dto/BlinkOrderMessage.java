package io.github.followsclosely.bricklink.dto;

import lombok.*;

import java.sql.Timestamp;

/**
 * Represents a message associated with a Bricklink order.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"from", "to", "dateSent", "subject"})
public class BlinkOrderMessage {
    /**
     * The subject of the message.
     */
    private String subject;

    /**
     * The contents of the message.
     */
    private String body;

    /**
     * The username of who sends the message.
     */
    private String from;

    /**
     * The username of who receives the message.
     */
    private String to;

    /**
     * The time the message was sent.
     */
    private Timestamp dateSent;
}

