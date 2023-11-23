import { Component, OnInit } from '@angular/core';
import { Interview } from 'src/entities/Interview';
import { InterviewService } from 'src/app/services/interview.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-interview-reports',
  templateUrl: './interview-reports.component.html',
  styleUrls: ['./interview-reports.component.css'],
})
export class InterviewReportsComponent implements OnInit {
  allInterviews!: Interview[];
  interviewByDate!: Interview[];
  date!: string;
  showAll: boolean = true;

  constructor(
    private interviewService: InterviewService,
    private tokenService: TokenService
  ) {}

  ngOnInit(): void {
    this.interviewService
      .getInterviewsByEmployerId(this.tokenService.getToken()!)
      .subscribe({
        next: (result: Interview[]) => {
          this.allInterviews = result;
          console.log(this.allInterviews);
        },
        error: (error) => {
          console.error('Error fetching interviews by employer: ' + error.error);
        },
      });
  }

  getAllInterviews() {
    this.interviewService
      .getInterviewsByEmployerId(this.tokenService.getToken()!)
      .subscribe({
        next: (result: Interview[]) => {
          this.allInterviews = result;
          console.log(this.allInterviews);
        },
        error: (error) => {
          console.error('Error fetching interviews by employer: ' + error.error);
        },
      });
  }

  sendDate() {
    this.interviewService
      .getInterviewsByDate(this.date, this.tokenService.getToken()!)
      .subscribe({
        next: (result: Interview[]) => {
          this.interviewByDate = result;
          this.showAll = false;
          console.log(this.interviewByDate);
        },
        error: (error) => {
          console.error('Error fetching interviews by date: ' + error);
        },
      });
  }
}
