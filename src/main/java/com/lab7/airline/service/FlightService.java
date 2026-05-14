package com.lab7.airline.service;

import com.lab7.airline.model.Flight;
import com.lab7.airline.model.FlightStatus;
import com.lab7.airline.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Рейс не знайдено: " + id));
    }

    public Flight saveFlight(Flight flight) {
        if (flight.getId() == null &&
                flightRepository.existsByFlightNumber(flight.getFlightNumber())) {
            throw new IllegalArgumentException(
                    "Рейс з номером " + flight.getFlightNumber() + " вже існує");
        }
        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long id, Flight updated) {
        Flight existing = getFlightById(id);
        existing.setFlightNumber(updated.getFlightNumber());
        existing.setDepartureCity(updated.getDepartureCity());
        existing.setArrivalCity(updated.getArrivalCity());
        existing.setDepartureTime(updated.getDepartureTime());
        existing.setArrivalTime(updated.getArrivalTime());
        existing.setStatus(updated.getStatus());
        return flightRepository.save(existing);
    }

    public Flight changeStatus(Long id, FlightStatus newStatus) {
        Flight flight = getFlightById(id);
        flight.setStatus(newStatus);
        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public List<Flight> getFlightsByStatus(FlightStatus status) {
        return flightRepository.findByStatus(status);
    }
}