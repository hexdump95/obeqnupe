package ar.sergiovillanueva.obeqnupe.repository;

import ar.sergiovillanueva.obeqnupe.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Page<Company> findAll(Specification<Company> spec, Pageable pageable);
    Page<Company> findBySkillsEmpty(Pageable pageable);
}
