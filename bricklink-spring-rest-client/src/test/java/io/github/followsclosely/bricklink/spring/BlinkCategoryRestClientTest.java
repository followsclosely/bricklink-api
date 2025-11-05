package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.dto.BlinkCategory;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import io.github.followsclosely.bricklink.oauth.BlinkAuthSignerFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class BlinkCategoryRestClientTest {

    @Test
    public void testGetCategory165() throws Exception {
        BlinkCategoryRestClient client = new BlinkCategoryRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<BlinkCategory> response = client.getCategory(165L);
        System.out.println(response);

        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals("Creator", response.getData().getName());
        // Add more assertions if BlinkCategory has fields to check, e.g. name, type, etc.
    }

    @Test
    public void testGetCategory171() throws Exception {
        BlinkCategoryRestClient client = new BlinkCategoryRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<BlinkCategory> response = client.getCategory(171L);
        System.out.println(response);

        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals("Creator", response.getData().getName());
        // Add more assertions if BlinkCategory has fields to check, e.g. name, type, etc.
    }

    @Test
    public void testGetCategory1068() throws Exception {
        BlinkCategoryRestClient client = new BlinkCategoryRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<BlinkCategory> response = client.getCategory(1068L);
        System.out.println(response);

        assertNotNull(response);
        assertNotNull(response.getData());
        //The api does not return names for sub categories - I need to figure this out!
        assertNull(response.getData().getName());
        // Add more assertions if BlinkCategory has fields to check, e.g. name, type, etc.
    }

    @Test
    public void testGetCategories() throws Exception {
        BlinkCategoryRestClient client = new BlinkCategoryRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<List<BlinkCategory>> response = client.getCategories();

        assertNotNull(response);
        assertNotNull(response.getData());
        log.info("Retrieved {} categories.", response.getData().size());
        // Optionally, check for a known category by id or name if available
    }
}