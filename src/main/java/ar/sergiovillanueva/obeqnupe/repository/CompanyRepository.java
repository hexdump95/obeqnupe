package ar.sergiovillanueva.obeqnupe.repository;

import ar.sergiovillanueva.obeqnupe.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
