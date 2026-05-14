package com.lab7.airline.controller;

import com.lab7.airline.model.CrewMember;
import com.lab7.airline.model.CrewRole;
import com.lab7.airline.service.CrewService;
import com.lab7.airline.service.FlightService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/crew")
public class CrewController {

    private final CrewService crewService;
    private final FlightService flightService;

    public CrewController(CrewService crewService, FlightService flightService) {
        this.crewService = crewService;
        this.flightService = flightService;
    }

    @GetMapping
    public String listCrew(Model model) {
        model.addAttribute("crew", crewService.getAllCrew());
        model.addAttribute("roles", CrewRole.values());
        return "crew/list";
    }

    @GetMapping("/new")
    @PreAuthorize("hasAnyRole('ADMIN','DISPATCHER')")
    public String newCrewForm(Model model) {
        model.addAttribute("member", new CrewMember());
        model.addAttribute("roles", CrewRole.values());
        return "crew/form";
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','DISPATCHER')")
    public String saveCrew(@ModelAttribute CrewMember member) {
        crewService.saveCrew(member);
        return "redirect:/crew";
    }

    @GetMapping("/assign/{flightId}")
    @PreAuthorize("hasAnyRole('ADMIN','DISPATCHER')")
    public String assignPage(@PathVariable Long flightId, Model model) {
        model.addAttribute("flight", flightService.getFlightById(flightId));
        model.addAttribute("crew", crewService.getAllCrew());
        model.addAttribute("assigned", crewService.getAssignmentsForFlight(flightId));
        return "crew/assign";
    }

    @PostMapping("/assign")
    @PreAuthorize("hasAnyRole('ADMIN','DISPATCHER')")
    public String assignCrew(@RequestParam Long flightId,
            @RequestParam Long crewId) {
        crewService.assignCrewToFlight(flightId, crewId);
        return "redirect:/crew/assign/" + flightId;
    }

    @PostMapping("/unassign/{assignmentId}")
    @PreAuthorize("hasAnyRole('ADMIN','DISPATCHER')")
    public String unassign(@PathVariable Long assignmentId,
            @RequestParam Long flightId) {
        crewService.removeAssignment(assignmentId);
        return "redirect:/crew/assign/" + flightId;
    }

    @PostMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCrew(@PathVariable Long id) {
        crewService.deleteCrew(id);
        return "redirect:/crew";
    }
}