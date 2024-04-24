//package org.lot_database.Airport;
//
//import jakarta.persistence.*;
//import org.lot_database.Flight.Flight;
//
//import java.util.List;
//
//@Entity
//public class Airport {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToMany(mappedBy = "departure_airport")
//    private List<Flight> departures;
//    @OneToMany(mappedBy = "arrival_airport")
//    private List<Flight> arrivals;
//
//
//
//}
