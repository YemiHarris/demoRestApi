package com.yemi.demo.api.controller;

import com.yemi.demo.api.model.Station;
import com.yemi.demo.api.service.StationService;
import com.yemi.demo.api.util.CustomError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/api")
public class StationController {

    public static final Logger logger = LogManager.getLogger(StationController.class);

    @Autowired
    StationService stationService; //Service for retrieving and manipulating station data.

    /**
     * Get Station by stationId.
     *
     * @param id {@link Long}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/station/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStationById(@PathVariable("id") Long id) {
        logger.info("Retreiving Station with id {}", id);

        Station station = stationService.findById(id);
        if (station == null) {
            logger.error("Station with id {} not found", id);
            CustomError error = new CustomError("Station with id " + id + " not found.");
            return new ResponseEntity(error, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Station>(station, HttpStatus.OK);
    }

    /**
     * Get Station by station name.
     *
     * @param name {@link String}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/station/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getStationByName(@PathVariable("name") String name) {
        logger.info("Retreiving Station with name {}", name);

        Station station = stationService.findByName(name);
        if (station == null) {
            logger.error("Station with name {} not found", name);
            CustomError error = new CustomError("Station with name " + name + " not found.");
            return new ResponseEntity(error, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Station>(station, HttpStatus.OK);
    }

    /**
     * Get Station by HDEnabled status.
     *
     * @param hdEnabled {@link boolean}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/station", method = RequestMethod.GET)
    public ResponseEntity<List<Station>> getStation(@RequestParam(value = "hdEnabled") boolean hdEnabled) {
        logger.info("Retreiving Station by HD enabled status {}", hdEnabled);
        List<Station> stations = stationService.findByHdEnabled(hdEnabled);
        if (stations.isEmpty()) {
            logger.error("Station with HDEnabled status of {} not found", hdEnabled);
            return new ResponseEntity(
                    new CustomError("Station with HDEnabled status of {" + hdEnabled + "} not found."),
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<List<Station>>(stations, HttpStatus.OK);
    }

    /**
     * Create new Station.
     *
     * @param station {@link Station}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/station", method = RequestMethod.POST)
    public ResponseEntity<?> createStation(@RequestBody Station station, UriComponentsBuilder builder) {
        logger.info("Creating Station : {}", station);

        if (stationService.doesStationExist(station)) {
            logger.error("Station with name {} already exists, unable to create", station.getName());
            return new ResponseEntity(
                    new CustomError("Station with name " + station.getName() + " already exists, unable to create"),
                    HttpStatus.CONFLICT
            );
        }

        stationService.saveStation(station);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/station/{id}").buildAndExpand(station.getStationId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     * Update existing station.
     *
     * @param id      {@link Long}
     * @param station {@link Station}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/station/id/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStation(@PathVariable("id") Long id, @RequestBody Station station) {
        logger.info("Updating Station with id: {}", id);

        Station currentStation = stationService.findById(id);

        if (currentStation == null) {
            logger.error("Unable to update. Station with id {} not found", id);
            return new ResponseEntity(
                    new CustomError("Unable to update. Station with id " + id + " not found"),
                    HttpStatus.NOT_FOUND
            );
        }

        station.setStationId(currentStation.getStationId());
        stationService.updateStation(station);
        return new ResponseEntity<Station>(station, HttpStatus.OK);
    }

    /**
     * Delete Station by Id.
     *
     * @param id {@link Long}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/station/id/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStation(@PathVariable("id") Long id) {
        logger.info("Deleting Station with id: {}", id);

        Station currentStation = stationService.findById(id);

        if (currentStation == null) {
            logger.error("Unable to delete. Station with id {} not found", id);
            return new ResponseEntity(
                    new CustomError("Unable to delete. Station with id " + id + " not found."),
                    HttpStatus.NOT_FOUND
            );
        }

        stationService.deleteStation(id);
        return new ResponseEntity<Station>(HttpStatus.OK);
    }
}
