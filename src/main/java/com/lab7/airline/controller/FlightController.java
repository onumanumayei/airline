package com.lab7.airline.controller;

import com.lab7.airline.model.Flight;
import com.lab7.airline.model.FlightStatus;
import com.lab7.airline.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public String listFlights(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        model.addAttribute("statuses", FlightStatus.values());
        return "flights/list";
    }

    @GetMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String newFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        model.addAttribute("statuses", FlightStatus.values());
        return "flights/form";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String createFlight(@Valid @ModelAttribute Flight flight,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("statuses", FlightStatus.values());
            return "flights/form";
        }
        flightService.saveFlight(flight);
        return "redirect:/flights";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String editFlightForm(@PathVariable Long id, Model model) {
        model.addAttribute("flight", flightService.getFlightById(id));
        model.addAttribute("statuses", FlightStatus.values());
        return "flights/form";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateFlight(@PathVariable Long id,
            @Valid @ModelAttribute Flight flight,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("statuses", FlightStatus.values());
            return "flights/form";
        }
        flightService.updateFlight(id, flight);
        return "redirect:/flights";
    }

    @PostMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return "redirect:/flights";
    }

    @GetMapping("/{id}")
    public String flightDetail(@PathVariable Long id, Model model) {
        model.addAttribute("flight", flightService.getFlightById(id));
        return "flights/detail";
    }
}