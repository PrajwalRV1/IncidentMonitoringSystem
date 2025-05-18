export enum Severity {
    LOW = 'LOW',
    MEDIUM = 'MEDIUM',
    HIGH = 'HIGH'
  }
  
  export enum Status {
    OPEN = 'OPEN',
    IN_PROGRESS = 'IN_PROGRESS',
    RESOLVED = 'RESOLVED'
  }
  
  export interface Incident {
    id?: number;
    title: string;
    location: string;
    severity: Severity;
    status: Status;
    timestamp?: Date;
  }
  
  export interface IncidentDto {
    title: string;
    location: string;
    severity: Severity;
    status: Status;
  }