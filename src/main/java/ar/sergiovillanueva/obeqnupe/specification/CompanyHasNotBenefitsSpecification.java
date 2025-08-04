package ar.sergiovillanueva.obeqnupe.specification;

import ar.sergiovillanueva.obeqnupe.entity.Benefit;
import ar.sergiovillanueva.obeqnupe.entity.Company;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public class CompanyHasNotBenefitsSpecification implements Specification<Company> {
    private final List<Long> benefitIds;

    public CompanyHasNotBenefitsSpecification(List<Long> benefitIds) {
        this.benefitIds = benefitIds;
    }

    @Override
    public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Subquery<UUID> benefitSubquery = query.subquery(UUID.class);
        Root<Company> benefitRoot = benefitSubquery.from(Company.class);
        Join<Company, Benefit> benefitJoin = benefitRoot.join("benefits");

        benefitSubquery.select(benefitRoot.get("id"))
                .where(benefitJoin.get("id").in(benefitIds))
                .groupBy(benefitRoot.get("id"));

        return criteriaBuilder.not(root.get("id").in(benefitSubquery));
    }
}
