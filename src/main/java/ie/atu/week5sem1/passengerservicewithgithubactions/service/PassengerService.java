package ie.atu.week5sem1.passengerservicewithgithubactions.service;
//import org.springframework.stereotype.Service;

import ie.atu.week5sem1.passengerservicewithgithubactions.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    private final List<Passenger> store = new ArrayList<>();

    public List<Passenger> findAll() {
        return new ArrayList<>(store);
    }

    public Optional<Passenger> findById(String id) {
        for (Passenger p : store) {
            if (p.getPassengerId().equals(id)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public Optional<Passenger> updateById(String id, Passenger pUpdated) {
        for (Passenger p : store) {
            if (p.getPassengerId().equals(id)) {
                p.setName(pUpdated.getName());
                p.setEmail(pUpdated.getEmail());
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public Passenger create(Passenger p) {
        if (findById(p.getPassengerId()).isPresent()) {
            throw new IllegalStateException("passengerId already exists");
        }
        store.add(p);
        return p;
    }
}
