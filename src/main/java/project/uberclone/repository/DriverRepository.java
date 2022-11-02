package project.uberclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.uberclone.model.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
