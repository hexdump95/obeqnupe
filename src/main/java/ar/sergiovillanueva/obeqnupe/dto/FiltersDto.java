package ar.sergiovillanueva.obeqnupe.dto;

import ar.sergiovillanueva.obeqnupe.entity.Benefit;
import ar.sergiovillanueva.obeqnupe.entity.Location;
import ar.sergiovillanueva.obeqnupe.entity.Skill;

import java.util.ArrayList;
import java.util.List;

public class FiltersDto {
    private List<Benefit> benefits = new ArrayList<>();
    private List<Location> locations = new ArrayList<>();
    private List<Skill> skills = new ArrayList<>();

    public FiltersDto() {
    }

    public List<Benefit> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<Benefit> benefits) {
        this.benefits = benefits;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
