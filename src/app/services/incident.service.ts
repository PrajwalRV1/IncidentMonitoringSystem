import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Incident, IncidentDto } from '../models/incident.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class IncidentService {
  private apiUrl = `${environment.apiBaseUrl}/api/incidents`;

  constructor(private http: HttpClient) {}

  createIncident(incident: IncidentDto): Observable<Incident> {
    return this.http.post<Incident>(this.apiUrl, incident);
  }

  getAllIncidents(): Observable<Incident[]> {
    return this.http.get<Incident[]>(this.apiUrl);
  }
}