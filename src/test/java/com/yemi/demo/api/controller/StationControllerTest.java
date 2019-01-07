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
        Station station = new Station(
                10001L,
                "Station10001",
                true,
                "STA10001"
        );
        String expected = "{stationId:10001,name:Station10001,hdEnabled:true,callSign:STA10001}";
        Mockito.when(stationService.findById(10001L)).thenReturn(station);
        String uri = "/api/station/id/10001";

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
        //act
        //assert
    }

    @Test
    public void getStation() throws Exception {
        //arrange
        //act
        //assert
    }

    @Test
    public void createStation() throws Exception {
        //arrange
        //act
        //assert
    }

    @Test
    public void updateStation() throws Exception {
        //arrange
        //act
        //assert
    }

    @Test
    public void deleteStation() throws Exception {
        //arrange
        //act
        //assert
    }
}
