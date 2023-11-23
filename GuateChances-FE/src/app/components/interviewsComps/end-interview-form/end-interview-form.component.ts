import { Component, Input } from '@angular/core';
import { Interview } from 'src/entities/Interview';
import { InterviewService } from 'src/app/services/interview.service';

@Component({
  selector: 'app-end-interview-form',
  templateUrl: './end-interview-form.component.html',
  styleUrls: ['./end-interview-form.component.css'],
})
export class EndInterviewFormComponent {
  userNotes: string = '';
  @Input()
  interview!: Interview;

  constructor(private interviewService: InterviewService) {}

  hireUser() {
    console.log('before contract', this.interview);

    this.interview.interviewState = 1;
    this.interview.notes = this.userNotes;
    this.interview.application.state = 1;
    this.interview.application.seeker.isActive = 2;
    this.interview.application.offer.offerState = 2;
    this.interview.application.offer.seekerSelected = this.interview.application
      .seeker as any;
    this.interviewService.hireUser(this.interview).subscribe({
      next: (data) => {
        console.log('data', data);

      },
      error: (error) => {
        console.log('error', error);
      },
    });

    console.log('contracted', this.interview);
  }

  rejectUser() {
    this.interview.interviewState = 2;
    this.interview.notes = this.userNotes;
    this.interview.application.state = 2;
    console.log(this.interview);
    this.interviewService.rejectUser(this.interview).subscribe({
      next: (data) => {
        console.log('data', data);
      },
      error: (error) => {
        console.log('error', error);
      },
    });
  }
}
