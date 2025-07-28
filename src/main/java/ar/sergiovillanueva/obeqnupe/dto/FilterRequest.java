package ar.sergiovillanueva.obeqnupe.dto;

import java.util.ArrayList;
import java.util.List;

public class FilterRequest {
    private Integer locationId;
    private List<Integer> benefitIds = new ArrayList<>();
    private List<Integer> skillIds = new ArrayList<>();

    public FilterRequest() {
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public List<Integer> getBenefitIds() {
        return benefitIds;
    }

    public void setBenefitIds(List<Integer> benefitIds) {
        this.benefitIds = benefitIds;
    }

    public List<Integer> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<Integer> skillIds) {
        this.skillIds = skillIds;
    }
}
