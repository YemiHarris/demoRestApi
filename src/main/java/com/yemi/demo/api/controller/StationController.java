package com.yemi.demo.api.controller;

import com.yemi.demo.api.model.Station;
import com.yemi.demo.api.service.StationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class StationController {

    public static final Logger logger = LogManager.getLogger(StationController.class);

    @Autowired
    StationService stationService; //Service for retrieving and manipulating station data.

    /**
     * Get Station by stationId.
     *
     * @param id {@link long}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/station/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStationById(@PathVariable("id") long id) {
        logger.info("Retreiving Station with id {}", id);
        Station station = new Station();

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
        Station station = new Station();

        return new ResponseEntity<Station>(station, HttpStatus.OK);
    }

    /**
     * Get Station by HDEnabled status.
     * @param hdEnabled {@link Boolean}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/station", method = RequestMethod.GET)
    public ResponseEntity<?> getStation(@RequestParam(value = "hdEnabled", required = true) Boolean hdEnabled) {
        logger.info("Retreiving Station by HD enabled status {}", hdEnabled);
        Station station = new Station();

        return new ResponseEntity<Station>(station, HttpStatus.OK);
    }
}
