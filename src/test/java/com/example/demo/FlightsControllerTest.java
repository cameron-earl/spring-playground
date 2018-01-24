package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightsController.class)
public class FlightsControllerTest {

    @Autowired
    private MockMvc mvc;

//    @Test
//    public void testFlight() throws Exception {
//        this.mvc.perform(
//                get("/flights/flight")
//                        .accept(MediaType.APPLICATION_JSON_UTF8)
//                        .contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.departs", is("2017-04-21 14:34")))
//                .andExpect(jsonPath("$.tickets[0].passenger.firstName", is("Some name")))
//                .andExpect(jsonPath("$.tickets[0].passenger.lastName", is("Some other name")))
//                .andExpect(jsonPath("$.tickets[0].price", is(200)));
//    }

    @Test
    public void testFlights() throws Exception {
//        this.mvc.perform(
//                get("/flights")
//                        .accept(MediaType.APPLICATION_JSON_UTF8)
//                        .contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].departs", is("2017-04-21 14:34")))
//                .andExpect(jsonPath("$[0].tickets[0].passenger.firstName", is("Some name")))
//                .andExpect(jsonPath("$[0].tickets[0].passenger.lastName", is("Some other name")))
//                .andExpect(jsonPath("$[0].tickets[0].price", is(200)))
//                .andExpect(jsonPath("$[1].departs", is("2017-04-21 14:34")))
//                .andExpect(jsonPath("$[1].tickets[0].passenger.firstName", is("Some other name")))
//                .andExpect(jsonPath("$[1].tickets[0].passenger.lastName", is(nullValue())))
//                .andExpect(jsonPath("$[1].tickets[0].price", is(400)));
        this.mvc.perform(
                get("/flights")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", is("Some name")))
                .andExpect(jsonPath("$[0].Tickets[0].Price", is(200)));
    }
}
