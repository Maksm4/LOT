package org.lot_database.Passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/passengers")
public class PassengerController {
    private PassengerService service;

    @Autowired
    public PassengerController(PassengerService service) {
        this.service = service;
    }


    //dashboard get
    @GetMapping
    public String showPassengerDashboard() {
        return "passenger/passengerDashboard";
    }

    //create section
    @GetMapping("/create")
    public String getCreatePassengerForm(Model model)
    {
        model.addAttribute("passenger", new Passenger());
        return "passenger/createPassenger";
    }

    @PostMapping("/create")
    public RedirectView createPassenger(@ModelAttribute Passenger passenger)
    {
        service.createPassenger(passenger);
        return new RedirectView("/passengers", true,false);
    }

    //delete section

    @GetMapping("/delete")
    public String getDeletePassengerForm()
    {
        return "passenger/deletePassenger";
    }

    @PostMapping("/delete")
    public RedirectView deletePassenger(@RequestParam Long id)
    {
        service.deletePassenger(id);
        return new RedirectView("/passengers", true, false);
    }

    @GetMapping("/displayAll")
    public String getAllPassengers(Model model)
    {
        model.addAttribute("passengersList", service.getAllPassengers());
        return "passenger/displayPassengers";
    }

    @PostMapping("/update")
    public RedirectView updatePassenger(@RequestParam Long id, @RequestParam(required = false) String name,
                                  @RequestParam(required = false) String surname,@RequestParam(required = false) String telephone_number)
    {
        service.updatePassenger(id,name,surname, telephone_number);
        return new RedirectView("/passengers", true,false);
    }

    @GetMapping("/update")
    public String getUpdatePassengerForm()
    {
        return "passenger/updatePassenger";
    }

}
