package com.lab7.airline.controller.rest;

import com.lab7.airline.model.Flight;
import com.lab7.airline.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
// Дозволяємо запити з Vue (порт 5173 відрізняється від Spring порту 8080)
@CrossOrigin(origins = "http://localhost:5173")
public class FlightRestController {

    private final FlightService flightService;

    public FlightRestController(FlightService flightService) {
        this.flightService = flightService;
    }

    // GET /api/flights → повертає JSON-масив всіх рейсів
    @GetMapping
    public List<Flight> getAll() {
        return flightService.getAllFlights();
    }

    // GET /api/flights/1 → один рейс або 404
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(flightService.getFlightById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/flights → створити рейс, повертає 201 Created
    @PostMapping
    public ResponseEntity<Flight> create(@Valid @RequestBody Flight flight) {
        try {
            Flight saved = flightService.saveFlight(flight);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT /api/flights/1 → оновити існуючий рейс
    @PutMapping("/{id}")
    public ResponseEntity<Flight> update(@PathVariable Long id,
            @Valid @RequestBody Flight flight) {
        try {
            return ResponseEntity.ok(flightService.updateFlight(id, flight));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/flights/1 → видалити рейс, повертає 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            flightService.deleteFlight(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}