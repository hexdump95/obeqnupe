package ar.sergiovillanueva.obeqnupe.dto;

import java.util.ArrayList;
import java.util.List;

public class FilterRequest {
    private Long locationId;
    private Long companyTypeId;
    private List<Long> benefitIds = new ArrayList<>();
    private List<Long> excludedBenefitIds = new ArrayList<>();
    private List<Long> skillIds = new ArrayList<>();
    private List<Long> excludedSkillIds = new ArrayList<>();
    private String query;

    public FilterRequest() {
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getCompanyTypeId() {
        return companyTypeId;
    }

    public void setCompanyTypeId(Long companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

    public List<Long> getBenefitIds() {
        return benefitIds;
    }

    public void setBenefitIds(List<Long> benefitIds) {
        this.benefitIds = benefitIds;
    }

    public List<Long> getExcludedBenefitIds() {
        return excludedBenefitIds;
    }

    public void setExcludedBenefitIds(List<Long> excludedBenefitIds) {
        this.excludedBenefitIds = excludedBenefitIds;
    }

    public List<Long> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<Long> skillIds) {
        this.skillIds = skillIds;
    }

    public List<Long> getExcludedSkillIds() {
        return excludedSkillIds;
    }

    public void setExcludedSkillIds(List<Long> excludedSkillIds) {
        this.excludedSkillIds = excludedSkillIds;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

}
