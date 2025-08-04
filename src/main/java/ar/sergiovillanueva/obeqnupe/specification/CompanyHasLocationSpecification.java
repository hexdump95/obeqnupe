package ar.sergiovillanueva.obeqnupe.specification;

import ar.sergiovillanueva.obeqnupe.entity.Company;
import ar.sergiovillanueva.obeqnupe.entity.Location;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class CompanyHasLocationSpecification implements Specification<Company> {
    private final Long locationId;

    public CompanyHasLocationSpecification(Long locationId) {
        this.locationId = locationId;
    }

    @Override
    public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Location, Company> locationCompanyJoin = root.join("location");
        return criteriaBuilder.equal(locationCompanyJoin.get("id"), locationId);
    }
}
