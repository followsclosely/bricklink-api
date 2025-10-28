package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.BlinkOrderClient;
import io.github.followsclosely.bricklink.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class BlinkOrderRestClientTest {

    @Test
    void getOrders() throws IOException {
        BlinkOrderRestClient client = new BlinkOrderRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<List<BlinkOrder>> response = client.getOrders(BlinkOrderClient.Query.builder().direction(BlinkOrderClient.Query.Direction.out).build());

        assertNotNull(response);
        assertNotNull(response.getData());
        //25722880
        log.info("Retrieved {} orders.", response.getData().size());
    }

    @Test
    void getOrder() throws IOException {
        BlinkOrderRestClient client = new BlinkOrderRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<BlinkOrder> response = client.getOrder(25722880);

        assertNotNull(response);
        assertNotNull(response.getData());
    }

    @Test
    void getOrderItems() throws IOException {
        BlinkOrderRestClient client = new BlinkOrderRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<List<List<BlinkOrderItem>>> response = client.getOrderItems(29671299);

        assertNotNull(response);
        assertNotNull(response.getData());
    }

    @Test
    void getOrderMessages() throws IOException {
        BlinkOrderRestClient client = new BlinkOrderRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<List<BlinkOrderMessage>> response = client.getOrderMessages(29506181);

        assertNotNull(response);
        assertNotNull(response.getData());

        response.getData().forEach(System.out::println);
    }

    @Test
    void getOrderFeedback() throws IOException {
        BlinkOrderRestClient client = new BlinkOrderRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<List<BlinkOrderFeedback>> response = client.getOrderFeedback(29506181);

        assertNotNull(response);
        assertNotNull(response.getData());

        response.getData().forEach(System.out::println);
    }
}