import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { IncidentService } from '../../services/incident.service';
import { Severity, Status } from '../../models/incident.model';

@Component({
  selector: 'app-incident-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './incident-form.component.html',
  styleUrls: ['./incident-form.component.scss']
})
export class IncidentFormComponent {
  incidentForm: FormGroup;
  submitted = false;
  loading = false;
  
  severityOptions = Object.values(Severity);
  statusOptions = Object.values(Status);
  
  // Options for the address type dropdown
  addressTypes = ['On-Staff', 'Other'];
  selectedAddressType = this.addressTypes[0];

  constructor(
    private formBuilder: FormBuilder,
    private incidentService: IncidentService,
    private router: Router
  ) {
    this.incidentForm = this.formBuilder.group({
      title: ['', [Validators.required]],
      location: ['', [Validators.required]],
      severity: [Severity.MEDIUM, [Validators.required]],
      status: [Status.OPEN, [Validators.required]],
      addressType: ['On-Staff'],
      referenceNumber: [''],
      refArchiveNo: [''],
      incidentDateTime: ['', Validators.required],
      startDateTime: [''],
      endDateTime: [''],
      email: ['', [Validators.email]],
      createdBy: ['']
    });
  }

  get f() { return this.incidentForm.controls; }

  onSubmit(): void {
    this.submitted = true;

    if (this.incidentForm.invalid) {
      return;
    }

    this.loading = true;
    
    // Create incident DTO from form data
    const incidentDto = {
      title: this.f['title'].value,
      location: this.f['location'].value,
      severity: this.f['severity'].value,
      status: this.f['status'].value
    };

    this.incidentService.createIncident(incidentDto).subscribe({
      next: () => {
        this.loading = false;
        this.submitted = false;
        this.incidentForm.reset({
          severity: Severity.MEDIUM,
          status: Status.OPEN,
          addressType: 'On-Staff'
        });
        alert('Incident submitted successfully!');
        this.router.navigate(['/incidents']);
      },
      error: (error) => {
        console.error('Error submitting incident:', error);
        this.loading = false;
        alert('Error submitting incident. Please try again.');
      }
    });
  }
}