package ar.sergiovillanueva.obeqnupe.specification;

import ar.sergiovillanueva.obeqnupe.entity.Company;
import ar.sergiovillanueva.obeqnupe.entity.CompanyType;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class CompanyHasCompanyTypeSpecification implements Specification<Company> {
    private final Long companyTypeId;

    public CompanyHasCompanyTypeSpecification(Long companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

    @Override
    public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<CompanyType, Company> companyTypeCompanyJoin = root.join("companyType");
        return criteriaBuilder.equal(companyTypeCompanyJoin.get("id"), companyTypeId);
    }
}
