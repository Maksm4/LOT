package org.lot_database.Flight;

import jakarta.persistence.*;
import org.lot_database.Passenger.Passenger;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flight_number;
//    @ManyToOne
//    private Airport departure_airport;
//    @ManyToOne
//    private Airport arrival_airport;
    
    private String departureAirport;
    private String arrivalAirport;
    private LocalDate departureDate;
    @ManyToMany(mappedBy = "flights")
    private List<Passenger> passengers;
    private Integer availableSeats;


    public Long getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(Long flight_number) {
        this.flight_number = flight_number;
    }

    public String getDeparture_airport() {
        return departureAirport;
    }

    public void setDeparture_airport(String departure_airport) {
        this.departureAirport = departure_airport;
    }

    public String getArrival_airport() {
        return arrivalAirport;
    }

    public void setArrival_airport(String arrival_airport) {
        this.arrivalAirport = arrival_airport;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate date_of_flight) {
        this.departureDate = date_of_flight;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Integer getAvailable_seats() {
        return availableSeats;
    }

    public void setAvailable_seats(Integer available_seats) {
        this.availableSeats = available_seats;
    }
}
