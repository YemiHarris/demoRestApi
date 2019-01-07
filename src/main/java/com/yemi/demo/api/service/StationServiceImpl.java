package com.yemi.demo.api.service;

import com.yemi.demo.api.model.Station;
import com.yemi.demo.api.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("stationService")
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository stationRepository;

    public void setStationRepository(StationRepository repository) {
        this.stationRepository = repository;
    }

    /**
     * Get station by stationId.
     *
     * @param stationId {@link Long}
     * @return {@link Station}
     */
    @Override
    public Station findById(Long stationId) {

        Optional<Station> station = stationRepository.findById(stationId);
        return station.orElse(null);
    }

    /**
     * Get station by name.
     *
     * @param name {@link String}
     * @return {@link Station}
     */
    @Override
    public Station findByName(String name) {
        Station station = stationRepository.findByName(name);
        return station;
    }

    /**
     * Get station by HDEnabled status.
     *
     * @param hdEnabled {@link boolean}
     * @return {@link Station}
     */
    @Override
    public List<Station> findByHdEnabled(boolean hdEnabled) {
        List<Station> stations = stationRepository.findByHdEnabled(hdEnabled);
        if (stations.isEmpty()) {
            return new ArrayList<>();
        }
        return stations;
    }

    /**
     * Save station.
     *
     * @param station {@link Station}
     */
    @Override
    public void saveStation(Station station) {
        stationRepository.save(station);
    }

    /**
     * Update station.
     *
     * @param station {@link Station}
     */
    @Override
    public void updateStation(Station station) {
        stationRepository.save(station);
    }

    /**
     * Delete station.
     *
     * @param stationId {@link Station}
     */
    @Override
    public void deleteStation(Long stationId) {
        stationRepository.deleteById(stationId);

    }

    /**
     * Checks if stations exists by name.
     *
     * @param station {@link Station}
     * @return {@link boolean}
     */
    public boolean doesStationExist(Station station) {
        return findByName(station.getName()) != null;
    }
}
