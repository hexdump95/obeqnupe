package ar.sergiovillanueva.obeqnupe.entity;

import jakarta.persistence.*;

import java.util.*;

@Table
@Entity
public class Company {
    @Id
    private UUID id;
    private String name;
    private String permalink;
    @Column(length = 1000)
    private String page;
    private int upvotes;
    private int votes;
    private float score;
    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_type_id")
    private CompanyType companyType;
    @ManyToMany
    @JoinTable(
            name = "company_skill",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "company_benefit",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "benefit_id")
    )
    private Set<Benefit> benefits = new HashSet<>();
    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(
            name = "company_company_rating",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "company_rating_list_id")
    )
    private List<CompanyRating> companyRatingList = new ArrayList<>();

    public Company() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<Benefit> getBenefits() {
        return benefits;
    }

    public void setBenefits(Set<Benefit> benefits) {
        this.benefits = benefits;
    }

    public List<CompanyRating> getCompanyRatingList() {
        return companyRatingList;
    }

    public void setCompanyRatingList(List<CompanyRating> companyRatingList) {
        this.companyRatingList = companyRatingList;
    }
}
