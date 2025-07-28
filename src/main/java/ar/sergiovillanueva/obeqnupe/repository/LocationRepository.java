package ar.sergiovillanueva.obeqnupe.repository;

import ar.sergiovillanueva.obeqnupe.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
