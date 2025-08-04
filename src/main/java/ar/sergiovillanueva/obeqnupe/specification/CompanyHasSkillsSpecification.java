package ar.sergiovillanueva.obeqnupe.specification;

import ar.sergiovillanueva.obeqnupe.entity.Company;
import ar.sergiovillanueva.obeqnupe.entity.Skill;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public class CompanyHasSkillsSpecification implements Specification<Company> {
    private final List<Long> skillIds;

    public CompanyHasSkillsSpecification(List<Long> skillIds) {
        this.skillIds = skillIds;
    }

    @Override
    public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);
        Subquery<UUID> skillSubquery = query.subquery(UUID.class);
        Root<Company> skillRoot = skillSubquery.from(Company.class);
        Join<Company, Skill> skillJoin = skillRoot.join("skills");

        skillSubquery.select(skillRoot.get("id"))
                .where(skillJoin.get("id").in(skillIds))
                .groupBy(skillRoot.get("id"))
                .having(criteriaBuilder.equal(
                        criteriaBuilder.countDistinct(skillJoin.get("id")),
                        skillIds.size()
                ));

        return root.get("id").in(skillSubquery);
    }
}
