package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.BlinkItemClient;
import io.github.followsclosely.bricklink.dto.BlinkItem;
import io.github.followsclosely.bricklink.dto.BlinkItemType;
import io.github.followsclosely.bricklink.dto.BlinkOrder;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class BlinkItemRestClientTest {

    @Test
    void testGetItem() throws Exception {
        BlinkItemRestClient client = new BlinkItemRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<BlinkItem> response = client.getItem(BlinkItemType.SET, "10350-1");
        assertNotNull(response);
        assertNotNull(response.getData());
        log.info("Retrieved item: {}", response.getData().getName());
    }

    @Test
    void testGetPriceGuide() throws Exception {
        BlinkItemRestClient client = new BlinkItemRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<BlinkItem.BlinkPriceGuide> response = client.getPriceGuide(BlinkItemType.SET, "10350-1",
                BlinkItemClient.PriceGuideQuery.builder()
                        .condition(BlinkItemClient.PriceGuideQuery.Condition.USED)
                        .guideType(BlinkItemClient.PriceGuideQuery.GuideType.SOLD)
                        .countryCode("USA")
                        .build());
        assertNotNull(response);
        assertNotNull(response.getData());
        log.info("Retrieved item: {}", response.getData());
    }

    @Test
    void testGetKnownColors() throws IOException {
        BlinkItemRestClient client = new BlinkItemRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<List<BlinkItem.KnownColor>> response = client.getKnownColors(BlinkItemType.PART, "3001");

        assertNotNull(response);
        assertNotNull(response.getData());

        response.getData().forEach(System.out::println);
    }

    @Test
    void testGetImages() throws IOException {
        BlinkItemRestClient client = new BlinkItemRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<BlinkItem.Image> response = client.getImage(BlinkItemType.PART, "3001", 0);

        assertNotNull(response);
        assertNotNull(response.getData());

        System.out.println(response.getData());
    }

    @Test
    void testGetElementId() throws IOException {
        BlinkItemRestClient client = new BlinkItemRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<List<BlinkItem.ElementIdMapping>> response = client.getElementId(BlinkItemType.PART, "3001");

        assertNotNull(response);
        assertNotNull(response.getData());

        response.getData().forEach(System.out::println);
    }

    @Test
    void testGetItemNumber() throws IOException {
        BlinkItemRestClient client = new BlinkItemRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<List<BlinkItem.ElementIdMapping>> response = client.getItemNumber("6552094");

        assertNotNull(response);
        assertNotNull(response.getData());

        response.getData().forEach(System.out::println);
    }
}


