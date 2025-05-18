import { Routes } from '@angular/router';
import { IncidentFormComponent } from './components/incident-form/incident-form.component';
import { IncidentListComponent } from './components/incident-list/incident-list.component';

export const routes: Routes = [
  { path: '', redirectTo: '/incidents', pathMatch: 'full' },
  { path: 'incidents', component: IncidentListComponent },
  { path: 'create', component: IncidentFormComponent },
  { path: '**', redirectTo: '/incidents' }
];