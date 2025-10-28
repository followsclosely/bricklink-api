package io.github.followsclosely.bricklink;

import io.github.followsclosely.bricklink.dto.BlinkItem;
import io.github.followsclosely.bricklink.dto.BlinkItemType;
import io.github.followsclosely.bricklink.dto.BlinkResponse;


public interface BlinkItemClient {

    BlinkResponse<BlinkItem> getItem(BlinkItemType type, String number );


}
