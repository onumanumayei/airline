package com.lab7.airline.controller.rest;

import com.lab7.airline.model.CrewMember;
import com.lab7.airline.service.CrewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crew")
@CrossOrigin(origins = "http://localhost:5173")
public class CrewRestController {

    private final CrewService crewService;

    public CrewRestController(CrewService crewService) {
        this.crewService = crewService;
    }

    // GET /api/crew → список всього екіпажу
    @GetMapping
    public List<CrewMember> getAll() {
        return crewService.getAllCrew();
    }

    // GET /api/crew/1 → один запис
    @GetMapping("/{id}")
    public ResponseEntity<CrewMember> getById(@PathVariable Long id) {
        return crewService.getAllCrew().stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/crew → додати члена екіпажу
    @PostMapping
    public ResponseEntity<CrewMember> create(@RequestBody CrewMember member) {
        CrewMember saved = crewService.saveCrew(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT /api/crew/1 → оновити
    @PutMapping("/{id}")
    public ResponseEntity<CrewMember> update(@PathVariable Long id,
            @RequestBody CrewMember member) {
        member.setId(id);
        try {
            return ResponseEntity.ok(crewService.saveCrew(member));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/crew/1 → видалити
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            crewService.deleteCrew(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}