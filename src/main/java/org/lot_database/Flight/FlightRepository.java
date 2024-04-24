package org.lot_database.Flight;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface FlightRepository extends CrudRepository<Flight, Long> {

    List<Flight> findAllByDepartureAirport(String departureAirport);

    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDate(String departureAirport, String arrivalAirport, LocalDate departureDate);

    List<Flight> findAllByArrivalAirport(String arrivalAirport);

    List<Flight> findAllByDepartureDate(LocalDate departureDate);

    List<Flight> findAllByDepartureAirportAndArrivalAirport(String departureAirport, String arrivalAirport);
    List<Flight> findAllByDepartureAirportAndDepartureDate(String departureAirport, LocalDate departureDate);
    List<Flight> findAllByArrivalAirportAndDepartureDate(String arrivalAirport, LocalDate departureDate);


}
