package org.lot_database.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {
    private FlightService service;

    @Autowired
    public FlightController(FlightService service) {
        this.service = service;
    }

    @GetMapping
    public String showFlightDashboard() {
        return "flight/flightDashboard";
    }

    // Create Flight
    @GetMapping("/create")
    public String getCreateFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "flight/createFlight";
    }

    @PostMapping("/create")
    public RedirectView createFlight(@ModelAttribute Flight flight) {
        service.createFlight(flight);
        return new RedirectView("/flights", true, false);
    }

    // Delete Flight
    @GetMapping("/delete")
    public String getDeleteFlightForm() {
        return "flight/deleteFlight";
    }

    @PostMapping("/delete")
    public RedirectView deleteFlight(@RequestParam Long id) {
        service.deleteFlight(id);
        return new RedirectView("/flights", true, false);
    }

    // Update Flight
    @GetMapping("/update")
    public String getUpdateFlightForm() {
        return "flight/updateFlight";
    }

    @PostMapping("/update")
    public RedirectView updateFlight(@RequestParam Long id,
                                     @RequestParam(required = false) String departureAirport,
                                     @RequestParam(required = false) String arrivalAirport,
                                     @RequestParam(required = false) LocalDate departureDate,
                                     @RequestParam(required = false) Integer availableSeats)
    {
        service.updateFlight(id, departureAirport, arrivalAirport, departureDate, availableSeats);
        return new RedirectView("/flights", true, false);
    }


    @GetMapping("/filter")
    public String getFlightsForm(@RequestParam(required = false) String departureAirport,
                                 @RequestParam(required = false) String arrivalAirport,
                                 @RequestParam(required = false) LocalDate departureDate,
                                 @RequestParam(required = false) Boolean availableSeats,
                                 Model model) {
        List<Flight> flights;
        if (departureAirport != null || arrivalAirport != null || departureDate != null || availableSeats != null) {
            flights = service.getFilteredFlights(departureAirport, arrivalAirport, departureDate, availableSeats);
        } else {
            flights = service.getAllFlights();
        }
        model.addAttribute("flightList", flights);
        return "flight/filterFlight";
    }

    @GetMapping("/passToFlight")
    public String getPassToFlightForm()
    {
        return "flight/addPassengerToFlight";
    }

    @PostMapping("/passToFlight")
    public RedirectView addPassengerToFlight(@RequestParam Long passengerId,
                                             @RequestParam Long flightId)
    {
        service.addPassengerToFlight(passengerId, flightId);
        return new RedirectView("/flights", true, false);
    }

    @GetMapping("/delPassFromFlight")
    public String getPassFromFlightForm()
    {
        return "flight/delPassengerFromFlight";
    }

    @PostMapping("/delPassFromFlight")
    public RedirectView delPassengerFromFLight(@RequestParam Long passengerId,
                                             @RequestParam Long flightId)
    {
        service.deletePassengerFromFlight(passengerId, flightId);
        return new RedirectView("/flights", true, false);
    }

}
