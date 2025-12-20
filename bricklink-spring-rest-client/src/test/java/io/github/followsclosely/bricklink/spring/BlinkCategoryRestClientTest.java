package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.dto.BlinkCategory;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import io.github.followsclosely.bricklink.oauth.BlinkAuthSignerFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class BlinkCategoryRestClientTest {

    private static BlinkCategoryRestClient client = null;

    @BeforeAll
    public static void init() throws IOException {
        BlinkCategoryRestClientTest.client = new BlinkCategoryRestClient(BlinkAuthSignerFactory.newInstance());
    }

    @Test
    public void testGetCategory165() throws Exception {
        BlinkResponse<BlinkCategory> response = client.getCategory(150L);
        System.out.println(response);

        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals("Minifigure, Torso", response.getData().getName());
        // Add more assertions if BlinkCategory has fields to check, e.g. name, type, etc.
    }

    @Test
    public void testGetCategory171() throws Exception {
        BlinkResponse<BlinkCategory> response = client.getCategory(171L);
        System.out.println(response);

        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals("Creator", response.getData().getName());
        // Add more assertions if BlinkCategory has fields to check, e.g. name, type, etc.
    }

    @Test
    public void testGetCategory1068() throws Exception {
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
        BlinkResponse<List<BlinkCategory>> response = client.getCategories();

        assertNotNull(response);
        assertNotNull(response.getData());
        log.info("Retrieved {} categories.", response.getData().size());
        // Optionally, check for a known category by id or name if available
    }
}