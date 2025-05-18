package com.prajwalrv.incident_monitoring.dataacces;

import com.prajwalrv.incident_monitoring.models.Incident;
import com.prajwalrv.incident_monitoring.repository.IncidentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class IncidentDataAccess implements IncidentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Incident save(Incident incident) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("insert_incident")
                .registerStoredProcedureParameter("p_title", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_location", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_severity", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_status", String.class, ParameterMode.IN)
                .setParameter("p_title", incident.getTitle())
                .setParameter("p_location", incident.getLocation())
                .setParameter("p_severity", incident.getSeverity().name())
                .setParameter("p_status", incident.getStatus().name());

        query.execute();
        return incident; // Note: ID is not returned by the stored procedure
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Incident> findAll() {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("get_all_incidents", Incident.class);
        return query.getResultList();
    }
}