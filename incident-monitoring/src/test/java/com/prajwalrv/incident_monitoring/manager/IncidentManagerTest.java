package com.prajwalrv.incident_monitoring.manager;

import com.prajwalrv.incident_monitoring.dtos.IncidentDto;
import com.prajwalrv.incident_monitoring.models.Incident;
import com.prajwalrv.incident_monitoring.repository.IncidentRepository;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IncidentManagerTest {

    @Mock
    private IncidentRepository repository;

    @Mock
    private MeterRegistry meterRegistry;

    @InjectMocks
    private IncidentManager manager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        meterRegistry = new SimpleMeterRegistry();
        manager = new IncidentManager(repository, meterRegistry);
    }

    @Test
    void createIncident_success() {
        IncidentDto dto = new IncidentDto();
        dto.setTitle("Test Incident");
        dto.setLocation("Test Location");
        dto.setSeverity(Incident.Severity.MEDIUM);
        dto.setStatus(Incident.Status.OPEN);

        Incident incident = new Incident();
        incident.setTitle(dto.getTitle());
        incident.setLocation(dto.getLocation());
        incident.setSeverity(dto.getSeverity());
        incident.setStatus(dto.getStatus());
        incident.setTimestamp(LocalDateTime.now());

        when(repository.save(any(Incident.class))).thenReturn(incident);

        Incident result = manager.createIncident(dto);

        assertEquals(dto.getTitle(), result.getTitle());
        verify(repository, times(1)).save(any(Incident.class));
    }

    @Test
    void getAllIncidents_success() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(0, manager.getAllIncidents().size());
        verify(repository, times(1)).findAll();
    }
}