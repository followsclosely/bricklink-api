package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.dto.BlinkColor;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class BlinkColorRestClientTest {

    @Test
    public void testGetColor() throws Exception {
        BlinkColorRestClient client = new BlinkColorRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<BlinkColor> response = client.getColor(1);
        System.out.println(response);

        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals("White", response.getData().getName());
        assertEquals("FFFFFF", response.getData().getHex());
    }

    @Test
    public void testGetColors() throws Exception {
        BlinkColorRestClient client = new BlinkColorRestClient(BlinkAuthSignerFactory.newInstance());
        BlinkResponse<List<BlinkColor>> response = client.getColors();

        assertNotNull(response);
        assertNotNull(response.getData());

        log.info("Retrieved {} colors.", response.getData().size());
        Optional<BlinkColor> color = response.getData().stream().filter(c -> c.getId() == 86).findFirst();

        assertTrue(color.isPresent());
        assertEquals("Light Bluish Gray", color.get().getName());

    }

}