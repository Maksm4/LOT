package org.lot_database.Passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    private PassengerRepository repository;

    @Autowired
    public PassengerService(PassengerRepository repository) {
        this.repository = repository;
    }

    public void createPassenger(Passenger passenger) {
        try {
            repository.save(passenger);
        }  catch (Exception e) {
            throw new RuntimeException("something went wrong when adding a passenger to database");
        }
    }

    public void deletePassenger(Long id) {
        try {
            repository.deleteById(id);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<Passenger> getAllPassengers() {
        try {
            return (List<Passenger>)repository.findAll();

        } catch (Exception e) {
            throw new RuntimeException("couldn't get the list of passengers");
        }
    }

    public void updatePassenger(Long id, String name, String surname, String telephone_number )
    {
       Optional<Passenger> passengerOptional =  repository.findById(id);
       if (passengerOptional.isPresent()){
           Passenger passenger = passengerOptional.get();
           if (!name.isEmpty()){
               passenger.setName(name);
               System.out.println("name set");
           }
           if (!surname.isEmpty()){
               passenger.setSurname(surname);
               System.out.println("surname set");
           }
           if (!telephone_number.isEmpty()){
               passenger.setTelephone_number(telephone_number);
               System.out.println("tel set");
           }

           repository.save(passenger);
       } else {
           throw  new RuntimeException("this id is not present in the database");
       }
    }



}
