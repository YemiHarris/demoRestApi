package com.yemi.demo.api.service;

import com.yemi.demo.api.model.Station;

import java.util.ArrayList;
import java.util.List;

public class StationServiceImpl implements StationService {

    private static List<Station> stationList;

    static {
        stationList = populateStationsList();
    }

    @Override
    public Station findById(String stationId) {
        return null;
    }

    @Override
    public Station findByName(String name) {
        return null;
    }

    @Override
    public Station findByHdEnabled(Boolean hdEnabled) {
        return null;
    }

    @Override
    public void saveStation(Station station) {

    }

    @Override
    public void updateStation(Station station) {

    }

    @Override
    public void deleteStation(String stationId) {

    }

    /**
     * Creates dummy list of stations.
     * @return {@link List} of {@link Station}
     */
    private static List<Station> populateStationsList() {
        List<Station> stations = new ArrayList<>();
        stations.add(new Station("A1", "station1", true, "STA1"));
        stations.add(new Station("A2", "station2", true, "STA2"));
        stations.add(new Station("A3", "station3", true, "STA3"));
        return stations;
    }
}
