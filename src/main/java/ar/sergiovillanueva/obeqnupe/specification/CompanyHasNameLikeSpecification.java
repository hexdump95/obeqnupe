package ar.sergiovillanueva.obeqnupe.specification;

import ar.sergiovillanueva.obeqnupe.entity.Company;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class CompanyHasNameLikeSpecification implements Specification<Company> {
    private final String name;

    public CompanyHasNameLikeSpecification(String name) {
        this.name = name;
    }

    @Override
    public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(
                criteriaBuilder.upper(root.get("name")),
                "%" + name.toUpperCase() + "%"
        );
    }
}
