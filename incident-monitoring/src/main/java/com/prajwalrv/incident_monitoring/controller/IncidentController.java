package com.prajwalrv.incident_monitoring.controller;

import com.prajwalrv.incident_monitoring.dtos.IncidentDto;
import com.prajwalrv.incident_monitoring.models.Incident;
import com.prajwalrv.incident_monitoring.manager.IncidentManager;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentManager manager;

    public IncidentController(IncidentManager manager) {
        this.manager = manager;
    }

    @PostMapping
    public ResponseEntity<Incident> createIncident(@Valid @RequestBody IncidentDto dto) {
        Incident incident = manager.createIncident(dto);
        return ResponseEntity.ok(incident);
    }

    @GetMapping
    public ResponseEntity<List<Incident>> getAllIncidents() {
        return ResponseEntity.ok(manager.getAllIncidents());
    }
}