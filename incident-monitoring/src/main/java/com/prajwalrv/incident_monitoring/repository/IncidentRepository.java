package com.prajwalrv.incident_monitoring.repository;

import com.prajwalrv.incident_monitoring.models.Incident;
import java.util.List;

public interface IncidentRepository {
    Incident save(Incident incident);
    List<Incident> findAll();
}