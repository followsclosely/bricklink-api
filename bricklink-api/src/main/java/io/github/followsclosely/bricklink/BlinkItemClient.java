package io.github.followsclosely.bricklink;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.followsclosely.bricklink.dto.BlinkItem;
import io.github.followsclosely.bricklink.dto.BlinkItemType;
import io.github.followsclosely.bricklink.dto.BlinkResponse;
import io.github.followsclosely.bricklink.dto.BlinkPriceGuide;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


public interface BlinkItemClient {

    BlinkResponse<BlinkItem> getItem(BlinkItemType type, String number );

    BlinkResponse<BlinkPriceGuide> getPriceGuide(BlinkItemType type, String number, PriceGuideQuery priceGuideQuery);

    @Data
    @Builder
    class PriceGuideQuery {
        private Integer colorId;
        private GuideType guideType;
        private NewOrUsed newOrUsed;
        private String countryCode;
        private Region region;
        private String currencyCode;
        private VatOption vat;

        @RequiredArgsConstructor
        public enum NewOrUsed {
            NEW("N"),
            USED("U");

            private final String value;
            @JsonValue
            public String getValue() { return value; }
            @JsonCreator
            public static NewOrUsed fromValue(String value) {
                for (NewOrUsed n : values()) {
                    if (n.value.equalsIgnoreCase(value)) return n;
                }
                throw new IllegalArgumentException("Unknown newOrUsed: " + value);
            }
        }

        @RequiredArgsConstructor
        public enum GuideType {
            SOLD("sold"),
            STOCK("stock");

            private final String value;
            @JsonValue public String getValue() { return value; }
            @JsonCreator public static GuideType fromValue(String value) {
                for (GuideType g : values()) {
                    if (g.value.equalsIgnoreCase(value)) return g;
                }
                throw new IllegalArgumentException("Unknown guideType: " + value);
            }
        }

        @RequiredArgsConstructor
        public enum Region {
            ASIA("asia"),
            AFRICA("africa"),
            NORTH_AMERICA("north_america"),
            SOUTH_AMERICA("south_america"),
            MIDDLE_EAST("middle_east"),
            EUROPE("europe"),
            EU("eu"),
            OCEANIA("oceania");

            private final String value;

            @JsonValue public String getValue() { return value; }
            @JsonCreator public static Region fromValue(String value) {
                for (Region r : values()) {
                    if (r.value.equalsIgnoreCase(value)) return r;
                }
                throw new IllegalArgumentException("Unknown region: " + value);
            }
        }

        @RequiredArgsConstructor
        public enum VatOption {
            EXCLUDE("N"),
            INCLUDE("Y"),
            NORWAY("O");

            private final String value;
            @JsonValue public String getValue() { return value; }
            @JsonCreator public static VatOption fromValue(String value) {
                for (VatOption v : values()) {
                    if (v.value.equalsIgnoreCase(value)) return v;
                }
                throw new IllegalArgumentException("Unknown vat option: " + value);
            }
        }
    }
}
