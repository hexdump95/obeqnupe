package ar.sergiovillanueva.obeqnupe.specification;

import ar.sergiovillanueva.obeqnupe.entity.Benefit;
import ar.sergiovillanueva.obeqnupe.entity.Company;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public class CompanyHasBenefitsSpecification implements Specification<Company> {
    private final List<Long> benefitIds;

    public CompanyHasBenefitsSpecification(List<Long> benefitIds) {
        this.benefitIds = benefitIds;
    }

    @Override
    public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);
        Subquery<UUID> benefitSubquery = query.subquery(UUID.class);
        Root<Company> benefitRoot = benefitSubquery.from(Company.class);
        Join<Company, Benefit> benefitJoin = benefitRoot.join("benefits");

        benefitSubquery.select(benefitRoot.get("id"))
                .where(benefitJoin.get("id").in(benefitIds))
                .groupBy(benefitRoot.get("id"))
                .having(criteriaBuilder.equal(
                        criteriaBuilder.countDistinct(benefitJoin.get("id")),
                        benefitIds.size()
                ));

        return root.get("id").in(benefitSubquery);
    }
}
