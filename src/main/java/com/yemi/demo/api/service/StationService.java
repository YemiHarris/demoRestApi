package com.yemi.demo.api.service;

import com.yemi.demo.api.model.Station;

import java.util.List;

public interface StationService {
    Station findById(String stationId);

    Station findByName(String name);

    List<Station> findByHdEnabled(Boolean hdEnabled);

    String saveStation(Station station);

    void updateStation(Station station);

    void deleteStation(Station station);

    boolean doesStationExist(Station station);
}
