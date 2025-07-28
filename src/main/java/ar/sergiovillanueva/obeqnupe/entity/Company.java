package ar.sergiovillanueva.obeqnupe.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String permalink;
    @Column(length = 1000)
    private String page;
    private int reviews;
    @ManyToOne
    private City city;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "company_skill",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "company_benefit",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "benefit_id")
    )
    private List<Benefit> benefits = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinTable(
            name = "company_company_rating",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "company_rating_list_id")
    )
    private List<CompanyRating> companyRatingList = new ArrayList<>();

    public Company() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Benefit> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<Benefit> benefits) {
        this.benefits = benefits;
    }

    public void addBenefit(Benefit benefit) {
        this.benefits.add(benefit);
    }

    public List<CompanyRating> getCompanyRatingList() {
        return companyRatingList;
    }

    public void setCompanyRatingList(List<CompanyRating> companyRatingList) {
        this.companyRatingList = companyRatingList;
    }
}
