package com.prajwalrv.incident_monitoring.manager;

import com.prajwalrv.incident_monitoring.dtos.IncidentDto;
import com.prajwalrv.incident_monitoring.models.Incident;
import com.prajwalrv.incident_monitoring.repository.IncidentRepository;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncidentManager {

    private final IncidentRepository repository;
    private final Counter incidentCounter;

    public IncidentManager(IncidentRepository repository, MeterRegistry meterRegistry) {
        this.repository = repository;
        this.incidentCounter = Counter.builder("incident.submission.rate")
                .description("Number of incidents submitted per minute")
                .register(meterRegistry);
    }

    public Incident createIncident(IncidentDto dto) {
        Incident incident = new Incident();
        incident.setTitle(dto.getTitle());
        incident.setLocation(dto.getLocation());
        incident.setSeverity(dto.getSeverity());
        incident.setStatus(dto.getStatus());
        incident.setTimestamp(LocalDateTime.now());

        incidentCounter.increment();
        return repository.save(incident);
    }

    public List<Incident> getAllIncidents() {
        return repository.findAll();
    }
}