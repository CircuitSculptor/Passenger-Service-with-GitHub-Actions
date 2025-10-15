package ie.atu.week5sem1.passengerservicewithgithubactions.controller;

import ie.atu.week5sem1.passengerservicewithgithubactions.model.Passenger;
import ie.atu.week5sem1.passengerservicewithgithubactions.service.PassengerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

     private final PassengerService Service;

     @GetMapping
     public PassengerController(PassengerService Service) {
         this.Service = Service;
     }

     @GetMapping("/{id}")
     public ResponseEntity<Passenger> getOne(@PathVariable String id) {
         Optional<Passenger> Passenger=Service.findById(id);
         if (maybe.isPresent()) {
             return ResponseEntity.ok(Passenger.get());
         } else {
             return ResponseEntity.notFound().build();
         }
     }

     @PostMapping
     public ResponseEntity<Passenger> create(@RequestBody Passenger Passenger p) {
         Passenger created = service.create(p);
         return ResponseEntity
                 .create(URI.create("/api/passengers/" + created.getPassengerId()))
                 .body(created);
     }
}
