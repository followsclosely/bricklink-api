package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.dto.BlinkItem;
import io.github.followsclosely.bricklink.dto.BlinkItemType;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class BlinkCatalogItemRestClientTest {

    @Test
    void testGetItem() throws Exception {
        BlinkItemRestClient client = new BlinkItemRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<BlinkItem> response = client.getItem(BlinkItemType.SET, "10350-1");
        assertNotNull(response);
        assertNotNull(response.getData());
        log.info("Retrieved item: {}", response.getData().getName());
    }
}


