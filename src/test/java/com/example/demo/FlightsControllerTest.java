package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightsController.class)
public class FlightsControllerTest {

    @Autowired
    private MockMvc mvc;
    private ObjectMapper objectMapper = new ObjectMapper();

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

    @Test
    public void testTicketsSumJackson() throws Exception {

        HashMap<String, Object> person1 = new HashMap<String, Object>(){
            {
                put("firstName", "Some name");
                put("lastName", "Some other name");
            }
        };

        HashMap<String, Object> person2 = new HashMap<String, Object>(){
            {
                put("firstName", "Name B");
                put("lastName", "Name C");
            }
        };

        HashMap<String, Object> ticket1 = new HashMap<String, Object>(){
            {
                put("passenger", person1);
                put("price", 200);
            }
        };

        HashMap<String, Object> ticket2 = new HashMap<String, Object>(){
            {
                put("passenger", person2);
                put("price", 150);
            }
        };

        HashMap<String, Object> data = new HashMap<String, Object>(){
            {
                put("tickets", new HashMap[]{ticket1, ticket2});
            }
        };

        String json = objectMapper.writeValueAsString(data);

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));
    }

    @Test
    public void testTicketsSumFile() throws Exception {
//        String json = getJSON("/data.json"); // no such thing!
        InputStream is = Test.class.getResourceAsStream("/data.json");
        Scanner s = new Scanner(is).useDelimiter("\\A");
        String json = s.hasNext() ? s.next() : "";

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));
    }

    @Test
    public void testTicketsStringLiteral() throws Exception {
        String json = "{\n" +
                "  \"tickets\": [\n" +
                "    {\n" +
                "      \"passenger\": {\n" +
                "        \"firstName\": \"Some name\",\n" +
                "        \"lastName\": \"Some other name\"\n" +
                "      },\n" +
                "      \"price\": 200\n" +
                "    },\n" +
                "    {\n" +
                "      \"passenger\": {\n" +
                "        \"firstName\": \"Name B\",\n" +
                "        \"lastName\": \"Name C\"\n" +
                "      },\n" +
                "      \"price\": 150\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));
    }
}
