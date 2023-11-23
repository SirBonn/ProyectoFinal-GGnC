import { Component, OnInit } from '@angular/core';
import { Application } from 'src/entities/Application';
import { Interview } from 'src/entities/Interview';
import { AplicationService } from 'src/app/services/aplication.service';
import { InterviewService } from 'src/app/services/interview.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-seeker-reports',
  templateUrl: './seeker-reports.component.html',
  styleUrls: ['./seeker-reports.component.css'],
})
export class SeekerReportsComponent implements OnInit {
  allApplications!: Application[];
  pendingApplications!: Application[];
  selectedApplications!: Application[];
  rejectedApplications!: Application[];
  interveiwApplications!: Interview[];

  constructor(
    private applicationService: AplicationService,
    private interviewService: InterviewService,
    private tokenService: TokenService
  ) {}

  ngOnInit() {
    this.applicationService
      .getApplicationsBySeekerId(this.tokenService.getToken()!)
      .subscribe({
        next: (data) => {
          this.allApplications = data;
          this.pendingApplications = this.allApplications.filter(
            (application) => application.state == 0
          );
          this.selectedApplications = this.allApplications.filter(
            (application) => application.state == 1
          );
          this.rejectedApplications = this.allApplications.filter(
            (application) => application.state == 2
          );
          console.log('all: ', this.allApplications);
          console.log('pending: ', this.pendingApplications);
          console.log('active: ', this.selectedApplications);
          console.log('rejected: ', this.rejectedApplications);
        },
        error: (error) => {
          console.log(error.error);
        },
      });

    this.interviewService
      .getInterviewsBySeekerId(this.tokenService.getToken()!)
      .subscribe({
        next: (data) => {
          this.interveiwApplications = data;
          console.log('interviews: ', this.interveiwApplications);
        },
        error: (error) => {
          console.log(error.error);
        },
      });
  }
}
