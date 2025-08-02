package ar.sergiovillanueva.obeqnupe.service;

import ar.sergiovillanueva.obeqnupe.dto.*;
import ar.sergiovillanueva.obeqnupe.entity.Benefit;
import ar.sergiovillanueva.obeqnupe.entity.Company;
import ar.sergiovillanueva.obeqnupe.entity.Location;
import ar.sergiovillanueva.obeqnupe.entity.Skill;
import ar.sergiovillanueva.obeqnupe.repository.BenefitRepository;
import ar.sergiovillanueva.obeqnupe.repository.CompanyRepository;
import ar.sergiovillanueva.obeqnupe.repository.LocationRepository;
import ar.sergiovillanueva.obeqnupe.repository.SkillRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final LocationRepository locationRepository;
    private final BenefitRepository benefitRepository;
    private final SkillRepository skillRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, LocationRepository locationRepository, BenefitRepository benefitRepository, SkillRepository skillRepository) {
        this.companyRepository = companyRepository;
        this.locationRepository = locationRepository;
        this.benefitRepository = benefitRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public FilterDataResponse getFilterData() {
        List<Location> locations = locationRepository.findAll();
        List<Benefit> benefits = benefitRepository.findAll();
        List<Skill> skills = skillRepository.findAll();
        FilterDataResponse filtersDto = new FilterDataResponse();
        filtersDto.setLocations(locations);
        filtersDto.setBenefits(benefits);
        filtersDto.setSkills(skills);
        return filtersDto;
    }

    @Override
    public PageDto<CompanyResponse> findAll(FilterRequest filter, Pageable pageable) {
        Page<Company> companyPage;
        if (filter.getLocationId() == null && filter.getSkillIds().isEmpty() && filter.getBenefitIds().isEmpty()
                && (filter.getQuery() == null || filter.getQuery().isEmpty())) {
            companyPage = companyRepository.findBySkillsEmpty(pageable);
        } else {
            Specification<Company> spec = (root, query, criteriaBuilder) -> {
                Predicate predicate = criteriaBuilder.conjunction();
                query.distinct(true);

                if (filter.getBenefitIds() != null && !filter.getBenefitIds().isEmpty()) {
                    Subquery<UUID> benefitSubquery = query.subquery(UUID.class);
                    Root<Company> benefitRoot = benefitSubquery.from(Company.class);
                    Join<Company, Benefit> benefitJoin = benefitRoot.join("benefits");

                    benefitSubquery.select(benefitRoot.get("id"))
                            .where(benefitJoin.get("id").in(filter.getBenefitIds()))
                            .groupBy(benefitRoot.get("id"))
                            .having(criteriaBuilder.equal(
                                    criteriaBuilder.countDistinct(benefitJoin.get("id")),
                                    filter.getBenefitIds().size()
                            ));

                    predicate = criteriaBuilder.and(
                            predicate,
                            root.get("id").in(benefitSubquery)
                    );
                }

                if (filter.getSkillIds() != null && !filter.getSkillIds().isEmpty()) {
                    Subquery<UUID> skillSubquery = query.subquery(UUID.class);
                    Root<Company> skillRoot = skillSubquery.from(Company.class);
                    Join<Company, Skill> skillJoin = skillRoot.join("skills");

                    skillSubquery.select(skillRoot.get("id"))
                            .where(skillJoin.get("id").in(filter.getSkillIds()))
                            .groupBy(skillRoot.get("id"))
                            .having(criteriaBuilder.equal(
                                    criteriaBuilder.countDistinct(skillJoin.get("id")),
                                    filter.getSkillIds().size()
                            ));

                    predicate = criteriaBuilder.and(
                            predicate,
                            root.get("id").in(skillSubquery)
                    );
                }

                if (filter.getLocationId() != null) {
                    Join<Location, Company> locationCompanyJoin = root.join("location");
                    predicate = criteriaBuilder.and(
                            predicate,
                            criteriaBuilder.equal(locationCompanyJoin.get("id"), filter.getLocationId())
                    );
                }

                if (filter.getQuery() != null && !filter.getQuery().isEmpty()) {
                    predicate = criteriaBuilder.and(
                            predicate,
                            criteriaBuilder.like(
                                    criteriaBuilder.upper(root.get("name")),
                                    "%" + filter.getQuery().toUpperCase() + "%"
                            ));
                }
                return predicate;

            };

            companyPage = companyRepository.findAll(spec, pageable);
        }
        List<CompanyResponse> companyResponses = new ArrayList<>();
        for (Company company : companyPage.getContent()) {
            CompanyResponse companyResponse = new CompanyResponse();
            companyResponse.setId(company.getId());
            companyResponse.setName(company.getName());
            companyResponse.setPage(company.getPage());
            companyResponse.setLocationName(company.getLocation().getName());
            companyResponse.setCompanyTypeName(company.getCompanyType().getName());
            companyResponses.add(companyResponse);
        }

        PageDto<CompanyResponse> response = new PageDto<>();
        response.setPageIndex(companyPage.getNumber() + 1);
        response.setTotalPages(companyPage.getTotalPages());
        response.setHasPreviousPage(companyPage.getNumber() + 1 > 1);
        response.setHasNextPage(companyPage.getNumber() + 1 < companyPage.getTotalPages());
        response.setItems(companyResponses);

        return response;
    }

    @Override
    public CompanyDetailResponse findOne(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ExpressionException("")); // TODO: change this!

        CompanyDetailResponse companyResponse = new CompanyDetailResponse();
        companyResponse.setName(company.getName());
        companyResponse.setPage(company.getPage());
        companyResponse.setLocationName(company.getLocation().getName());
        companyResponse.setSkills(company.getSkills().stream().map(Skill::getName).collect(Collectors.toList()));
        companyResponse.setBenefits(company.getBenefits().stream().map(Benefit::getName).collect(Collectors.toList()));
        return companyResponse;
    }

}
