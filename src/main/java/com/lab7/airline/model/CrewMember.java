package com.lab7.airline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "crew_members")
public class CrewMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ім'я не може бути порожнім")
    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CrewRole role;

    @Column(name = "license_number", unique = true)
    private String licenseNumber;

    @Column(nullable = false)
    private boolean available = true;

    // @JsonIgnore — те саме: уникаємо рекурсії CrewMember → assignments →
    // CrewMember
    @JsonIgnore
    @OneToMany(mappedBy = "crewMember", cascade = CascadeType.ALL)
    private List<FlightAssignment> assignments = new ArrayList<>();

    public CrewMember() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public CrewRole getRole() {
        return role;
    }

    public void setRole(CrewRole role) {
        this.role = role;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<FlightAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<FlightAssignment> assignments) {
        this.assignments = assignments;
    }
}