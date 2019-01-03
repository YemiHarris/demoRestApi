package com.yemi.demo.api.service;

import com.yemi.demo.api.model.Station;

import java.util.List;

public interface StationService {
    Station findById(Long stationId);

    Station findByName(String name);

    List<Station> findByHdEnabled(boolean hdEnabled);

    void saveStation(Station station);

    void updateStation(Station station);

    void deleteStation(Long stationId);

    boolean doesStationExist(Station station);
}
