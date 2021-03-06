package com.yemi.demo.api.controller;

import com.yemi.demo.api.AbstractTest;
import com.yemi.demo.api.model.Station;
import com.yemi.demo.api.service.StationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class StationControllerTest extends AbstractTest {

    @MockBean
    private StationService stationService;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getStationById() throws Exception {
        //arrange
        String uri = "/api/station/id/10001";
        Station station = new Station(
                10001L,
                "Station10001",
                true,
                "STA10001"
        );
        String expected = "{stationId:10001,name:Station10001,hdEnabled:true,callSign:STA10001}";
        Mockito.when(stationService.findById(10001L)).thenReturn(station);

        //act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String actual = mvcResult.getResponse().getContentAsString();

        //assert
        assertEquals(200, status);
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    public void getStationByIdStationNotFound() throws Exception {
        // arrange
        String uri = "/api/station/id/10001";
        String expected = "{\"errorMsg\":\"Station with id 10001 not found.\"}";

        // act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String actual = mvcResult.getResponse().getContentAsString();

        // assert
        assertEquals(404, status);
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    public void getStationByName() throws Exception {
        //arrange
        String uri = "/api/station/name/Station10001";
        Station station = new Station(
                10001L,
                "Station10001",
                true,
                "STA10001"
        );
        String expected = "{stationId:10001,name:Station10001,hdEnabled:true,callSign:STA10001}";
        Mockito.when(stationService.findByName("Station10001")).thenReturn(station);

        //act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String actual = mvcResult.getResponse().getContentAsString();

        //assert
        assertEquals(200, status);
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    public void getStationByNameStationNotFound() throws Exception {
        // arrange
        String uri = "/api/station/name/dummyStation";
        String expected = "{\"errorMsg\":\"Station with name dummyStation not found.\"}";

        // act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String actual = mvcResult.getResponse().getContentAsString();

        // assert
        assertEquals(404, status);
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    public void getStation() throws Exception {
        //arrange
        String uri = "/api/station?hdEnabled=true";
        Station station1 = new Station(
                10001L,
                "Station10001",
                true,
                "STA10001"
        );

        Station station2 = new Station(
                10002L,
                "Station10002",
                true,
                "STA10002"
        );

        String expected = "[{\"stationId\":10001,\"name\":\"Station10001\",\"hdEnabled\":true,\"callSign\":\"STA10001\"}," +
                "{\"stationId\":10002,\"name\":\"Station10002\",\"hdEnabled\":true,\"callSign\":\"STA10002\"}]";
        Mockito.when(stationService.findByHdEnabled(true)).thenReturn(Arrays.asList(station1, station2));

        //act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String actual = mvcResult.getResponse().getContentAsString();
        System.out.println(actual);

        //assert
        assertEquals(200, status);
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    public void getStationNoneFound() throws Exception {
        // arrange
        String uri = "/api/station?hdEnabled=false";
        String expected = "{\"errorMsg\":\"Station with HDEnabled status of {false} not found.\"}";
        Mockito.when(stationService.findByHdEnabled(false)).thenReturn(new ArrayList<>());

        // act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String actual = mvcResult.getResponse().getContentAsString();

        // assert
        assertEquals(404, status);
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    public void createStation() throws Exception {
        //arrange
        String uri = "/api/station";
        Station station = new Station();
        station.setStationId(200L);
        station.setName("dummyStation");
        station.setHdEnabled(false);
        station.setCallSign("dummySTA");

        String inputJson = super.mapToJson(station);
        Mockito.when(stationService.doesStationExist(station)).thenReturn(false);

        //act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        //assert
        assertEquals(201, status);
    }

    @Test
    public void createStationFailed() throws Exception {
        //arrange
        String uri = "/api/station";
        String expected = "{\"errorMsg\":\"Station with name dummyStation already exists, unable to create\"}";
        Station station = new Station();
        station.setStationId(200L);
        station.setName("dummyStation");
        station.setHdEnabled(false);
        station.setCallSign("dummySTA");

        String inputJson = super.mapToJson(station);
        Mockito.when(stationService.doesStationExist(station)).thenReturn(true);

        //act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String actual = mvcResult.getResponse().getContentAsString();

        //assert
        assertEquals(409, status);
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    public void updateStation() throws Exception {
        //arrange
        String uri = "/api/station/id/10001";
        Station station = new Station();
        station.setStationId(10001L);
        station.setName("dummyStation");
        station.setHdEnabled(false);
        station.setCallSign("dummySTA");

        String inputJson = super.mapToJson(station);
        Mockito.when(stationService.findById(10001L)).thenReturn(station);

        //act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();

        //assert
        assertEquals(200, status);
    }

    @Test
    public void updateStationFailed() throws Exception {
        //arrange
        String uri = "/api/station/id/200";
        String expected = "{\"errorMsg\":\"Unable to update. Station with id 200 not found\"}";
        Station station = new Station();
        station.setStationId(200L);
        station.setName("dummyStation");
        station.setHdEnabled(false);
        station.setCallSign("dummySTA");

        String inputJson = super.mapToJson(station);
        Mockito.when(stationService.findById(200L)).thenReturn(null);

        //act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String actual = mvcResult.getResponse().getContentAsString();

        //assert
        assertEquals(404, status);
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    public void deleteStation() throws Exception {
        //arrange
        String uri = "/api/station/id/10001";
        Station station = new Station(
                10001L,
                "Station10001",
                true,
                "STA10001"
        );
        Mockito.when(stationService.findById(10001L)).thenReturn(station);

        //act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        //assert
        assertEquals(200, status);
    }

    @Test
    public void deleteStationDoesNotExist() throws Exception {
        //arrange
        String uri = "/api/station/id/10001";
        String expected = "{\"errorMsg\":\"Unable to delete. Station with id 10001 not found.\"}";
        Mockito.when(stationService.findById(10001L)).thenReturn(null);

        //act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        String actual = mvcResult.getResponse().getContentAsString();

        //assert
        assertEquals(404, status);
        JSONAssert.assertEquals(expected, actual, false);
    }
}
