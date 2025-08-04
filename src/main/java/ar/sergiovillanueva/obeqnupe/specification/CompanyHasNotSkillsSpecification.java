package ar.sergiovillanueva.obeqnupe.specification;

import ar.sergiovillanueva.obeqnupe.entity.Company;
import ar.sergiovillanueva.obeqnupe.entity.Skill;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public class CompanyHasNotSkillsSpecification implements Specification<Company> {
    private final List<Long> skillIds;

    public CompanyHasNotSkillsSpecification(List<Long> skillIds) {
        this.skillIds = skillIds;
    }

    @Override
    public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Subquery<UUID> skillSubquery = query.subquery(UUID.class);
        Root<Company> skillRoot = skillSubquery.from(Company.class);
        Join<Company, Skill> skillJoin = skillRoot.join("skills");

        skillSubquery.select(skillRoot.get("id"))
                .where(skillJoin.get("id").in(skillIds))
                .groupBy(skillRoot.get("id"));

        return criteriaBuilder.not(root.get("id").in(skillSubquery));
    }
}
