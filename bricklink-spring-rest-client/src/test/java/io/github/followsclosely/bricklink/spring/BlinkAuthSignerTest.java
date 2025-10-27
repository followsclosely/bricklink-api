package io.github.followsclosely.bricklink.spring;

import io.github.followsclosely.bricklink.BlinkAuthSigner;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

class BlinkAuthSignerTest {

    String API_BASE_URL = "https://api.bricklink.com/api/store/v1";

    @Test
    void newSigner() throws Exception {

        BlinkAuthSigner blinkAuthSigner = new BlinkAuthSigner(
                "1",
                "2",
                "3",
                "4");

        String path = "/item_mapping/6284676";

        BlinkAuthSigner.SignatureBuilder signatureBuilderForPart = blinkAuthSigner.signatureBuilder()
                .verb(BlinkAuthSigner.Method.GET)
                .uri(API_BASE_URL + path)
                .parameters(null);

        RestClient client = RestClient.builder().baseUrl(API_BASE_URL).build();
        String json = client.get()
                .uri(path)
                // Build the OAuth Authorization header
                .header(BlinkAuthSigner.HEADER, signatureBuilderForPart.buildAuthorizationHeader())
                .retrieve()
                .body(String.class);

        System.out.println(json);
    }
}