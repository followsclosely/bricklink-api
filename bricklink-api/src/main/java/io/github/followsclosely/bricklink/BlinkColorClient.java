package io.github.followsclosely.bricklink;

import io.github.followsclosely.bricklink.dto.BlinkColor;
import io.github.followsclosely.bricklink.dto.BlinkResponse;

import java.util.List;

public interface BlinkColorClient {
    BlinkResponse<BlinkColor> getColor(Integer id);

    BlinkResponse<List<BlinkColor>> getColors();
}
