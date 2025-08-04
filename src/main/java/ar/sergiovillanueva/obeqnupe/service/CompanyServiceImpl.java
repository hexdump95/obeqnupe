package ar.sergiovillanueva.obeqnupe.service;

import ar.sergiovillanueva.obeqnupe.dto.*;
import ar.sergiovillanueva.obeqnupe.entity.*;
import ar.sergiovillanueva.obeqnupe.repository.*;
import ar.sergiovillanueva.obeqnupe.specification.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final CompanyTypeRepository companyTypeRepository;
    private static final Integer PAGE_SIZE = 12;

    public CompanyServiceImpl(CompanyRepository companyRepository, LocationRepository locationRepository, BenefitRepository benefitRepository, SkillRepository skillRepository, CompanyTypeRepository companyTypeRepository) {
        this.companyRepository = companyRepository;
        this.locationRepository = locationRepository;
        this.benefitRepository = benefitRepository;
        this.skillRepository = skillRepository;
        this.companyTypeRepository = companyTypeRepository;
    }

    @Override
    public FilterDataResponse getFilterData() {
        List<Location> locations = locationRepository.findAll();
        List<Benefit> benefits = benefitRepository.findAll();
        List<Skill> skills = skillRepository.findAll();
        List<CompanyType> companyTypes = companyTypeRepository.findAll();
        FilterDataResponse filtersDto = new FilterDataResponse();
        filtersDto.setLocations(locations);
        filtersDto.setBenefits(benefits);
        filtersDto.setSkills(skills);
        filtersDto.setCompanyTypes(companyTypes);
        return filtersDto;
    }

    @Override
    public PageDto<CompanyResponse> findAll(FilterRequest filter, int page) {
        Page<Company> companyPage;
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, sort);

        Specification<Company> spec = new TrueSpecification<>();
        spec = spec.and(new CompanyFetchLazySpecification());

        if (filter.getLocationId() == null && filter.getSkillIds().isEmpty() && filter.getNotSkillIds().isEmpty()
                && filter.getBenefitIds().isEmpty() && filter.getNotBenefitIds().isEmpty()
                && filter.getCompanyTypeId() == null && (filter.getQuery() == null || filter.getQuery().isEmpty())) {
            spec = spec.and(new CompanyHasNoSkillsAndNoBenefitsSpecification());

        } else {
            if (filter.getBenefitIds() != null && !filter.getBenefitIds().isEmpty()) {
                spec = spec.and(new CompanyHasBenefitsSpecification(filter.getBenefitIds()));
            }

            if (filter.getNotBenefitIds() != null && !filter.getNotBenefitIds().isEmpty()) {
                spec = spec.and(new CompanyHasNotBenefitsSpecification(filter.getNotBenefitIds()));
            }

            if (filter.getSkillIds() != null && !filter.getSkillIds().isEmpty()) {
                spec = spec.and(new CompanyHasSkillsSpecification(filter.getSkillIds()));
            }

            if(filter.getNotSkillIds() != null && !filter.getNotSkillIds().isEmpty()) {
                spec = spec.and(new CompanyHasNotSkillsSpecification(filter.getNotSkillIds()));
            }

            if (filter.getLocationId() != null) {
                spec = spec.and(new CompanyHasLocationSpecification(filter.getLocationId()));
            }

            if (filter.getCompanyTypeId() != null) {
                spec = spec.and(new CompanyHasCompanyTypeSpecification(filter.getCompanyTypeId()));
            }

            if (filter.getQuery() != null && !filter.getQuery().isEmpty()) {
                spec = spec.and(new CompanyHasNameLikeSpecification(filter.getQuery()));
            }

        }
        companyPage = companyRepository.findAll(spec, pageable);
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
