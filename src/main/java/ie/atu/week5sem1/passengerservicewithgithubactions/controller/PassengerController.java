package ie.atu.week5sem1.passengerservicewithgithubactions.controller;

import ie.atu.week5sem1.passengerservicewithgithubactions.controller.errorHandling.PassengerNotFoundException;
import ie.atu.week5sem1.passengerservicewithgithubactions.model.Passenger;
import ie.atu.week5sem1.passengerservicewithgithubactions.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

     private final PassengerService service;

     public PassengerController(PassengerService service) {
         this.service = service;
     }

     @GetMapping
     public ResponseEntity<List<Passenger>> getAll() {
         return ResponseEntity.ok(service.findAll());
     }

     @GetMapping("/{id}")
     public ResponseEntity<Passenger> getOne(@PathVariable String id) {
         Optional<Passenger> maybe = service.findById(id);
         if (maybe.isPresent()) {
             return ResponseEntity.ok(maybe.get());
         } else {
             throw new PassengerNotFoundException(id);
         }
     }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> updateById(@PathVariable String id, @Valid @RequestBody Passenger pUpdated) {
        Optional<Passenger> maybeUpdated = service.updateById(id, pUpdated);
        if (maybeUpdated.isPresent()) {
            return ResponseEntity.ok(maybeUpdated.get());
        } else {
            throw new PassengerNotFoundException(id);
        }
    }

    @PostMapping
     public ResponseEntity<Passenger> create(@Valid @RequestBody Passenger p) {
         Passenger created = service.create(p);
         return ResponseEntity
                 .created(URI.create("/api/passengers/" + created.getPassengerId()))
                 .body(created);
     }

     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
         boolean removedId = service.deleteById(id);
         if (removedId) {
             return ResponseEntity.ok().build();
         }  else {
             return ResponseEntity.notFound().build();
         }
     }
}
