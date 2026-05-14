package com.lab7.airline.model;

import jakarta.persistence.*;

@Entity
@Table(name = "flight_assignments", uniqueConstraints = @UniqueConstraint(columnNames = { "flight_id",
        "crew_member_id" }))
public class FlightAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_member_id", nullable = false)
    private CrewMember crewMember;

    public FlightAssignment() {
    }

    public FlightAssignment(Flight flight, CrewMember crewMember) {
        this.flight = flight;
        this.crewMember = crewMember;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public CrewMember getCrewMember() {
        return crewMember;
    }

    public void setCrewMember(CrewMember crewMember) {
        this.crewMember = crewMember;
    }
}