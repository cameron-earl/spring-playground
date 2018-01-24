package com.example.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("flights")
public class FlightsController {

    @RequestMapping("")
    public List<Flight> flights() {
        Person passenger1 = new Person("Some name", null);
        List<Ticket> tickets1 = Arrays.asList(new Ticket(passenger1, 200));
        Date departs1 = new Date(117,3,21,7,34); //awful
        Flight flight1 = new Flight(departs1, tickets1);

//        Person passenger2 = new Person("Some other name", null);
//        List<Ticket> tickets2 = Arrays.asList(new Ticket(passenger2, 400));
//        Date departs2 = new Date(117,3,21,7,34); //awful
//        Flight flight2 = new Flight(departs2, tickets2);

        return Arrays.asList(flight1);
    }

//    @RequestMapping("flight")
//    public Flight flight() {
//        Person passenger = new Person("Some name", "Some other name");
//        List<Ticket> tickets = Arrays.asList(new Ticket(passenger, 200));
//        Date departs = new Date(117,3,21,7,34); //awful
//        Flight flight = new Flight(departs, tickets);
//        return flight;
//    }


    @RequestMapping("tickets/total")
    public Result total(@RequestBody Flight flight) {
        int sum = 0;
        int len = flight.tickets.size();
        for (int i = 0; i < len; i++ ){
            sum += flight.tickets.get(i).price;
        }
//        System.out.println(sum);
//        return sum;
        return new Result(sum);
    }

    public static class Result {
        private int result;

        @JsonCreator
        Result(
                @JsonProperty("result") int result
        ) {
            this.result = result;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }
    }

    public static class Flight {
        private Date departs;
        private List<Ticket> tickets;

        @JsonCreator
        Flight(
                @JsonProperty("tickets") List<Ticket> tickets
        ) {
            this.departs = null;
            this.tickets = tickets;
        }

        @JsonCreator
        Flight(
                @JsonProperty("departs") Date departs,
                @JsonProperty("tickets") List<Ticket> tickets
        ) {
            this.departs = departs;
            this.tickets = tickets;
        }

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        @JsonProperty("Departs")
        public Date getDeparts() {
            return departs;
        }

        @JsonProperty("departs")
        public void setDeparts(Date departs) {
            this.departs = departs;
        }

        @JsonProperty("Tickets")
        public List<Ticket> getTickets() {
            return tickets;
        }

        @JsonProperty("tickets")
        public void setTickets(List<Ticket> tickets) {
            this.tickets = tickets;
        }
    }

    public static class Ticket {
        private Person passenger;
        private int price;

        @JsonCreator
        Ticket(
                @JsonProperty("passenger") Person passenger,
                @JsonProperty("price") int price
        ){
            this.passenger = passenger;
            this.price = price;
        }

        @JsonProperty("Passenger")
        public Person getPassenger() {
            return passenger;
        }

        @JsonProperty("passenger")
        public void setPassenger(Person passenger) {
            this.passenger = passenger;
        }

        @JsonProperty("Price")
        public int getPrice() {
            return price;
        }

        @JsonProperty("price")
        public void setPrice(int price) {
            this.price = price;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Person {
        private String firstName;
        private String lastName;

        @JsonCreator
        Person(
                @JsonProperty("firstName") String firstName,
                @JsonProperty("lastName") String lastName
        ) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @JsonProperty("FirstName")
        public String getFirstName() {
            return firstName;
        }

        @JsonProperty("firstName")
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        @JsonProperty("LastName")
        public String getLastName() {
            return lastName;
        }

        @JsonProperty("lastName")
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}
