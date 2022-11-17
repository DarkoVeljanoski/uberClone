package project.uberclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.uberclone.model.entity.DriveRequestEntity;

@Repository
public interface DriveRequestRepository extends JpaRepository<DriveRequestEntity, Long> {
    DriveRequestEntity findByDriverId(Long driverId);
}
