package com.yemi.demo.api.service;

import com.yemi.demo.api.model.Station;

public interface StationService {
    Station findById(String stationId);

    Station findByName(String name);

    Station findByHdEnabled(Boolean hdEnabled);

    void saveStation(Station station);

    void updateStation(Station station);

    void deleteStation(String stationId);
}
