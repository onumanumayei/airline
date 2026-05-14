package com.lab7.airline.repository;

import com.lab7.airline.model.Flight;
import com.lab7.airline.model.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByStatus(FlightStatus status);

    boolean existsByFlightNumber(String flightNumber);
}