package ie.atu.week5sem1.passengerservicewithgithubactions.service;

import ie.atu.week5sem1.passengerservicewithgithubactions.model.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class PassengerServiceTest {

    private PassengerService service;

    @BeforeEach
    void setUp() {
        service = new PassengerService();
    }

    @Test
    void createThenFindById() {
        Passenger p = new Passenger("B1", "Bartek", "bartek@atu.ie");

        service.create(p);

        Optional<Passenger> found = service.findById("B1");
        assertTrue(found.isPresent());
        assertEquals("Bartek", found.get().getName());
    }

    @Test
    void duplicateIdThrows() {
        service.create(Passenger.builder()
                .passengerId("B2")
                .name("Bob")
                .email("b@atu.ie")
                .build());

        assertThrows(IllegalArgumentException.class,  () ->
                service.create(Passenger.builder()
                        .passengerId("B2")
                        .name("Bobby")
                        .email("bob@ex.com")
                        .build()));
    }
}
