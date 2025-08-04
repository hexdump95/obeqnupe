package ar.sergiovillanueva.obeqnupe.specification;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class TrueSpecification<T> implements Specification<T> {

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.conjunction();
    }
}
