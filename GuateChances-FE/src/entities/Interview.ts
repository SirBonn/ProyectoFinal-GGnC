import { Application } from './Application';

export interface Interview {
  idCode: number;
  application: Application;
  interviewDate: string;
  interviewTime: string;
  interviewState: number;
  direction: string;
  notes: string;
}
