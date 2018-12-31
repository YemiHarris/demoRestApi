package com.yemi.demo.api.controller;

import com.yemi.demo.api.model.Station;
import com.yemi.demo.api.service.StationService;
import com.yemi.demo.api.util.CustomError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @param id {@link String}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/station/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStationById(@PathVariable("id") String id) {
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
     * @param hdEnabled {@link Boolean}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/station", method = RequestMethod.GET)
    public ResponseEntity<List<Station>> getStation(@RequestParam(value = "hdEnabled") Boolean hdEnabled) {
        logger.info("Retreiving Station by HD enabled status {}", hdEnabled);
        List<Station> stations = stationService.findByHdEnabled(hdEnabled);
        if (stations.isEmpty()) {
            logger.error("Station with HDEnabled status of {} not found", hdEnabled);
            CustomError error = new CustomError("Station with HDEnabled status of {" + hdEnabled + "} not found.");
            return new ResponseEntity(error, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Station>>(stations, HttpStatus.OK);
    }
}
