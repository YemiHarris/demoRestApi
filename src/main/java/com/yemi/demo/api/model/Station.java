package com.yemi.demo.api.model;

import lombok.Data;

@Data
public class Station {
    private String stationId;
    private String name;
    private Boolean hdEnabled;
    private String callSign;

    public Station() {

    }

    public Station(String stationId, String name, Boolean hdEnabled, String callSign) {
        this.stationId = stationId;
        this.name = name;
        this.hdEnabled = hdEnabled;
        this.callSign = callSign;
    }
}
