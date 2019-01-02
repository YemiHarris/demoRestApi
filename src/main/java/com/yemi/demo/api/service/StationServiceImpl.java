package com.yemi.demo.api.service;

import com.yemi.demo.api.model.Station;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("stationService")
public class StationServiceImpl implements StationService {

    private static List<Station> stationList;

    static {
        stationList = populateStationsList();
    }

    /**
     * Get station by stationId.
     *
     * @param stationId {@link String}
     * @return {@link Station}
     */
    @Override
    public Station findById(String stationId) {
        for (Station station : stationList) {
            if (station.getStationId().equalsIgnoreCase(stationId)) {
                return station;
            }
        }
        return null;
    }

    /**
     * Get station by name.
     *
     * @param name {@link String}
     * @return {@link Station}
     */
    @Override
    public Station findByName(String name) {
        for (Station station : stationList) {
            if (station.getName().equalsIgnoreCase(name)) {
                return station;
            }
        }
        return null;
    }

    /**
     * Get station by HDEnabled status.
     *
     * @param hdEnabled {@link Boolean}
     * @return {@link Station}
     */
    @Override
    public List<Station> findByHdEnabled(Boolean hdEnabled) {
        List<Station> stations = new ArrayList<>();
        for (Station station : stationList) {
            if (station.getHdEnabled() == hdEnabled) {
                stations.add(station);
            }
        }

        if (stations.isEmpty()) {
            return new ArrayList<>();
        }

        return stations;
    }

    @Override
    public void saveStation(Station station) {
        //@todo Validate station data
        //@todo Save station to db
    }

    @Override
    public void updateStation(Station station) {
        //@todo Update existing station information in db
    }

    @Override
    public void deleteStation(String stationId) {
        //@todo Delete station record from db

    }

    public boolean doesStationExist(Station station) {
        //@todo Check Db for station by name
        return findByName(station.getName()) != null;
    }

    /**
     * Creates dummy list of stations.
     *
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
