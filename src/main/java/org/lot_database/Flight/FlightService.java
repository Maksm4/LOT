package org.lot_database.Flight;

import org.lot_database.Passenger.Passenger;
import org.lot_database.Passenger.PassengerRepository;
import org.lot_database.Passenger.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {
    private FlightRepository repository;
    private PassengerRepository passengerRepository;

    @Autowired
    public FlightService(FlightRepository repository, PassengerRepository passengerRepository) {
        this.repository = repository;
        this.passengerRepository = passengerRepository;
    }


    public void createFlight(Flight flight){
        try {
            repository.save(flight);
        }  catch (Exception e) {
            throw new RuntimeException("something went wrong when adding a passenger to database");
        }
    }

    public void deleteFlight(Long id) {
        try {
            repository.deleteById(id);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void updateFlight(Long id, String departureAirport, String arrivalAirport, LocalDate departureDate, Integer availableSeats)
    {
        Optional<Flight> flightOptional = repository.findById(id);
        if (flightOptional.isPresent()){
            Flight flight = flightOptional.get();
            if (departureAirport != null){
                flight.setDeparture_airport(departureAirport);
                System.out.println("dep exec");
            }
            if (arrivalAirport != null){
                flight.setArrival_airport(arrivalAirport);
                System.out.println("arr executed");
            }
            if (departureDate != null){
                flight.setDepartureDate(departureDate);
                System.out.println("date exec");
            }
            if (availableSeats != null){
                flight.setAvailable_seats(availableSeats);

            }
            repository.save(flight);
        } else {
            throw  new RuntimeException("This id is not present in the database");
        }
    }

    public List<Flight> getFilteredFlights(String departureAirport, String arrivalAirport, LocalDate departureDate, Boolean availableSeats)
    {
        if (departureAirport.isEmpty()){
            departureAirport = null;
        }

        if (arrivalAirport.isEmpty()){
            arrivalAirport = null;
        }
        List<Flight> flights = null;
        if (departureAirport != null && arrivalAirport != null && departureDate != null){
            flights = repository.findByDepartureAirportAndArrivalAirportAndDepartureDate(departureAirport,arrivalAirport,departureDate);
            System.out.println("all");
        }else if (departureAirport != null && arrivalAirport != null){
            flights = repository.findAllByDepartureAirportAndArrivalAirport(departureAirport,arrivalAirport);
            System.out.println("dep arr");
        }else if (departureAirport != null && departureDate != null){
            flights = repository.findAllByDepartureAirportAndDepartureDate(departureAirport,departureDate);
            System.out.println("dep dep");
        } else if (arrivalAirport != null && departureDate != null) {
            flights = repository.findAllByArrivalAirportAndDepartureDate(arrivalAirport,departureDate);
            System.out.println("arr dep");
        } else if (departureAirport != null){
            flights = repository.findAllByDepartureAirport(departureAirport);
            System.out.println("dep");
        }else if (arrivalAirport != null){
            flights = repository.findAllByArrivalAirport(arrivalAirport);
            System.out.println("arr");
        }else if (departureDate != null){
            flights = repository.findAllByDepartureDate(departureDate);
            System.out.println("date");
        }

        if (availableSeats != null && flights != null) {
            flights = flights.stream()
                    .filter(flight -> flight.getAvailable_seats() > 0)
                    .collect(Collectors.toList());
            System.out.println("seats");
        }

        return flights;
    }

    public List<Flight> getAllFlights() {
        try {
            return (List<Flight>)repository.findAll();

        } catch (Exception e) {
            throw new RuntimeException("couldn't get the list of Flights");
        }
    }

    public void addPassengerToFlight(Long passengerId, Long flightId)
    {
        Optional<Flight> flightOptional = repository.findById(flightId);
        Optional<Passenger> passengerOptional = passengerRepository.findById(passengerId);

        if (flightOptional.isPresent() && passengerOptional.isPresent())
        {
            Flight flight = flightOptional.get();
            Passenger passenger = passengerOptional.get();

            flight.getPassengers().add(passenger);
            passenger.getFlights().add(flight);
            flight.setAvailable_seats(flight.getAvailable_seats()-1);

            repository.save(flight);
            passengerRepository.save(passenger);
        }else
        {
            throw new RuntimeException("Not existent id of Passenger or Flight");
        }
    }

    public void deletePassengerFromFlight(Long passengerId, Long flightId) {
        Optional<Flight> flightOptional = repository.findById(flightId);
        Optional<Passenger> passengerOptional = passengerRepository.findById(passengerId);

        if (flightOptional.isPresent() && passengerOptional.isPresent()) {
            Flight flight = flightOptional.get();
            Passenger passenger = passengerOptional.get();

            flight.getPassengers().remove(passenger);
            passenger.getFlights().remove(flight);
            flight.setAvailable_seats(flight.getAvailable_seats()+1);

            repository.save(flight);
            passengerRepository.save(passenger);
        }else
        {
            throw new RuntimeException("Not existent id of Passenger or Flight");
        }

    }
}
