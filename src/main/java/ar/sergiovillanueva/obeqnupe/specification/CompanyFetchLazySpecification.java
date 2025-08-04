package ar.sergiovillanueva.obeqnupe.specification;

import ar.sergiovillanueva.obeqnupe.entity.Company;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class CompanyFetchLazySpecification implements Specification<Company> {

    @Override
    public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();
        if (query.getResultType() != Long.class) {
            root.fetch("location", JoinType.LEFT);
            root.fetch("companyType", JoinType.LEFT);
        }
        return predicate;
    }

}
