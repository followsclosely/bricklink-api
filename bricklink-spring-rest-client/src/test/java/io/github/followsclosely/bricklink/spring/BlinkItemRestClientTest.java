package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.BlinkItemClient;
import io.github.followsclosely.bricklink.dto.BlinkItem;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import io.github.followsclosely.bricklink.oauth.BlinkAuthSignerFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class BlinkItemRestClientTest {

    private static BlinkItemRestClient client = null;

    @BeforeAll
    public static void init() throws IOException {
        BlinkItemRestClientTest.client = new BlinkItemRestClient(BlinkAuthSignerFactory.newInstance());
    }

    @Test
    void testGetItem() throws Exception {
        BlinkResponse<BlinkItem> response = client.getItem(BlinkItem.Type.SET, "72050-1");
        assertNotNull(response);
        assertNotNull(response.getData());
        log.info("Retrieved item: {}", response.getData().getName());
    }

    @Test
    void testGetItemSubsets() throws Exception {
        BlinkResponse<List<BlinkItem.SubsetEntry>> response = client.getItemSubsets(BlinkItem.Type.SET, "71051-2",
                BlinkItemClient.ItemSubsetsQuery.builder()
                        .instructions(true)
                        .box(true)
                        .breakMinifigs(true)
                        .build()
        );
        assertNotNull(response);
        assertNotNull(response.getData());
        log.info("Retrieved inventory with {} entries!", response.getData().size());
    }

    //
    @Test
    void testGetItemSubsetsMINIFIGURE() throws Exception {
        BlinkResponse<List<BlinkItem.SubsetEntry>> response = client.getItemSubsets(BlinkItem.Type.MINIFIG, "sp007", null);
        assertNotNull(response);
        assertNotNull(response.getData());
        log.info("Retrieved inventory with {} entries!", response.getData().size());
    }

    @Test
    void testGetPriceGuide() throws Exception {
        BlinkResponse<BlinkItem.BlinkPriceGuide> response = client.getPriceGuide(BlinkItem.Type.SET, "10350-1",
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
        BlinkResponse<List<BlinkItem.KnownColor>> response = client.getKnownColors(BlinkItem.Type.PART, "3001");

        assertNotNull(response);
        assertNotNull(response.getData());

        response.getData().forEach(System.out::println);
    }

    @Test
    void testGetImages() throws IOException {
        BlinkResponse<BlinkItem.Image> response = client.getImage(BlinkItem.Type.PART, "3001", 0);

        assertNotNull(response);
        assertNotNull(response.getData());

        System.out.println(response.getData());
    }

    @Test
    void testGetSetImage() throws IOException {
        BlinkResponse<BlinkItem.Image> response = client.getImage(BlinkItem.Type.SET, "71051-2", 0);

        assertNotNull(response);
        assertNotNull(response.getData());

        System.out.println(response.getData());
    }

    @Test
    void testGetElementId() throws IOException {
        BlinkResponse<List<BlinkItem.ElementIdMapping>> response = client.getElementId(BlinkItem.Type.PART, "3001");

        assertNotNull(response);
        assertNotNull(response.getData());

        response.getData().forEach(System.out::println);
    }

    @Test
    void testGetItemNumber() throws IOException {
        BlinkResponse<List<BlinkItem.ElementIdMapping>> response = client.getItemNumber("6552094");

        assertNotNull(response);
        assertNotNull(response.getData());

        response.getData().forEach(System.out::println);
    }
}


