package ar.sergiovillanueva.obeqnupe.dto;

import java.util.ArrayList;
import java.util.List;

public class CompanyDetailResponse {
    private String name;
    private String page;
    private String locationName;
    private List<String> benefits = new ArrayList<>();
    private List<String> skills = new ArrayList<>();

    public CompanyDetailResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public List<String> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<String> benefits) {
        this.benefits = benefits;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
