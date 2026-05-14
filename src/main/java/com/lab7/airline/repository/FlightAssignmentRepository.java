package com.lab7.airline.repository;

import com.lab7.airline.model.FlightAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FlightAssignmentRepository extends JpaRepository<FlightAssignment, Long> {
    List<FlightAssignment> findByFlightId(Long flightId);

    List<FlightAssignment> findByCrewMemberId(Long crewMemberId);

    boolean existsByFlightIdAndCrewMemberId(Long flightId, Long crewMemberId);
}