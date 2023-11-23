import { Component, Input, OnInit } from '@angular/core';
import { Interview } from 'src/entities/Interview';
import { InterviewService } from 'src/app/services/interview.service';
import { Offer } from 'src/entities/Offer';
import { OfferService } from 'src/app/services/offer.service';
import { TokenService } from 'src/app/services/token.service';
import { Employer } from 'src/entities/Employer';
import { EmployersService } from 'src/app/services/employers.service';

@Component({
  selector: 'app-my-interviews',
  templateUrl: './my-interviews.component.html',
  styleUrls: ['./my-interviews.component.css'],
})
export class MyInterviewsComponent implements OnInit {
  allInterviews!: Interview[];
  activeInterviews!: Interview[];
  pastInterviews!: Interview[];
  offers!: Offer[];
  @Input() employer!: Employer;


  constructor(
    private interviewService: InterviewService,
    private offerService: OfferService,
    private employerService: EmployersService,
    private tokenService: TokenService
  ) {
    this.setInterviews();
  }

  ngOnInit(): void {}

  setInterviews() {
    this.interviewService
      .getInterviewsByEmployerId(this.tokenService.getToken()!)
      .subscribe({
        next: (interviews: Interview[]) => {
          console.log('interviews: ', interviews);
          this.allInterviews = interviews;
          this.activeInterviews = interviews.filter(
            (interview) => interview.interviewState === 0
          );
          this.pastInterviews = interviews.filter(
            (interview) => interview.interviewState !== 0
          );

        },
        error: (error) => {
          console.log('error al obtener las entrevistas', error.error);
        },
      });
  }
}
