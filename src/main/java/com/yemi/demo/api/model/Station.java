package com.yemi.demo.api.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "STATION")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stationId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "HD_ENABLED")
    private boolean hdEnabled;
    @Column(name = "CALL_SIGN")
    private String callSign;

    public Station() {

    }

    public Station(String name, boolean hdEnabled, String callSign) {
        this.name = name;
        this.hdEnabled = hdEnabled;
        this.callSign = callSign;
    }

    @Override
    public String toString() {
        return String.format(
                "Station[stationId=%d, name='%s', hdEnabled='%b', callSign='%s']",
                stationId, name, hdEnabled, callSign
        );
    }
}
