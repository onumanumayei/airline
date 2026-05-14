package com.lab7.airline.service;

import com.lab7.airline.model.*;
import com.lab7.airline.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CrewService {

    private final CrewMemberRepository crewRepo;
    private final FlightAssignmentRepository assignRepo;
    private final FlightRepository flightRepo;

    public CrewService(CrewMemberRepository crewRepo,
            FlightAssignmentRepository assignRepo,
            FlightRepository flightRepo) {
        this.crewRepo = crewRepo;
        this.assignRepo = assignRepo;
        this.flightRepo = flightRepo;
    }

    public List<CrewMember> getAllCrew() {
        return crewRepo.findAll();
    }

    public CrewMember saveCrew(CrewMember member) {
        return crewRepo.save(member);
    }

    public FlightAssignment assignCrewToFlight(Long flightId, Long crewId) {
        if (assignRepo.existsByFlightIdAndCrewMemberId(flightId, crewId)) {
            throw new IllegalStateException("Цей член екіпажу вже призначений на рейс");
        }
        Flight flight = flightRepo.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Рейс не знайдено"));
        CrewMember crew = crewRepo.findById(crewId)
                .orElseThrow(() -> new RuntimeException("Члена екіпажу не знайдено"));
        return assignRepo.save(new FlightAssignment(flight, crew));
    }

    public void removeAssignment(Long assignmentId) {
        assignRepo.deleteById(assignmentId);
    }

    public List<FlightAssignment> getAssignmentsForFlight(Long flightId) {
        return assignRepo.findByFlightId(flightId);
    }

    public void deleteCrew(Long id) {
        crewRepo.deleteById(id);
    }
}