package com.lab7.airline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Номер рейсу не може бути порожнім")
    @Column(name = "flight_number", unique = true, nullable = false)
    private String flightNumber;

    @NotBlank(message = "Місто відправлення не може бути порожнім")
    @Column(name = "departure_city", nullable = false)
    private String departureCity;

    @NotBlank(message = "Місто прибуття не може бути порожнім")
    @Column(name = "arrival_city", nullable = false)
    private String arrivalCity;

    @NotNull(message = "Дата відправлення обов'язкова")
    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @NotNull(message = "Дата прибуття обов'язкова")
    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightStatus status = FlightStatus.SCHEDULED;

    // @JsonIgnore — щоб при серіалізації в JSON не було нескінченної рекурсії
    // (Flight → assignments → Flight → assignments → ...)
    @JsonIgnore
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlightAssignment> assignments = new ArrayList<>();

    public Flight() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public List<FlightAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<FlightAssignment> assignments) {
        this.assignments = assignments;
    }
}