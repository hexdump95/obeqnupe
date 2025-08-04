package ar.sergiovillanueva.obeqnupe.specification;

import ar.sergiovillanueva.obeqnupe.entity.Benefit;
import ar.sergiovillanueva.obeqnupe.entity.Company;
import ar.sergiovillanueva.obeqnupe.entity.Skill;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class CompanyHasNoSkillsAndNoBenefitsSpecification implements Specification<Company> {

    @Override
    public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Skill, Company> skillCompanyJoin = root.join("skills", JoinType.LEFT);
        Join<Benefit, Company> benefitCompanyJoin = root.join("benefits", JoinType.LEFT);
        return criteriaBuilder.and(
                criteriaBuilder.isNull(skillCompanyJoin.get("id")),
                criteriaBuilder.isNull(benefitCompanyJoin.get("id"))
        );
    }

}
