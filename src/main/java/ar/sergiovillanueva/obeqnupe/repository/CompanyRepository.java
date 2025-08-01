package ar.sergiovillanueva.obeqnupe.repository;

import ar.sergiovillanueva.obeqnupe.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Page<Company> findAll(Specification<Company> spec, Pageable pageable);
    Page<Company> findBySkillsEmpty(Pageable pageable);
}
