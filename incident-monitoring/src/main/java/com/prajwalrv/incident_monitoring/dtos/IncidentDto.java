package com.prajwalrv.incident_monitoring.dtos;

import com.prajwalrv.incident_monitoring.models.Incident;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class IncidentDto {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Severity is required")
    private Incident.Severity severity;

    @NotNull(message = "Status is required")
    private Incident.Status status;

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Incident.Severity getSeverity() { return severity; }
    public void setSeverity(Incident.Severity severity) { this.severity = severity; }
    public Incident.Status getStatus() { return status; }
    public void setStatus(Incident.Status status) { this.status = status; }
}