package ie.atu.week5sem1.passengerservicewithgithubactions.controller;

import ie.atu.week5sem1.passengerservicewithgithubactions.model.Passenger;
import ie.atu.week5sem1.passengerservicewithgithubactions.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

     private final PassengerService service;

     @GetMapping
     public PassengerController(PassengerService Service) {
         this.service = Service;
     }

     @GetMapping("/{id}")
     public ResponseEntity<Passenger> getOne(@PathVariable String id) {
         Optional<Passenger> maybe = service.findById(id);
         if (maybe.isPresent()) {
             return ResponseEntity.ok(maybe.get());
         } else {
             return ResponseEntity.notFound().build();
         }
     }

     @PostMapping
     public ResponseEntity<Passenger> create(@Valid @RequestBody Passenger p) {
         Passenger created = service.create(p);
         return ResponseEntity
                 .created(URI.create("/api/passengers/" + created.getPassengerId()))
                 .body(created);
     }
}
