package org.example.entity;

public enum ZoneEnum {
    METROPOLE("metropole"),
    ALSACE_MOSELLE("alsace-moselle"),
    GUADELOUPE("guadeloupe"),
    GUYANE("guyane"),
    LA_REUNION("la-reunion"),
    MARTINIQUE("martinique"),
    MAYOTTE("mayotte");


    private final String apiName;

    ZoneEnum(String apiName) {
        this.apiName = apiName;
    }

    public String getApiName() {
        return apiName;
    }
}