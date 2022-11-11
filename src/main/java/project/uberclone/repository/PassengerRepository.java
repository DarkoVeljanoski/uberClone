package project.uberclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.uberclone.model.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    boolean existsByEmail(String email);
}
