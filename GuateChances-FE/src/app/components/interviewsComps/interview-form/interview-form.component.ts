import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Application } from 'src/entities/Application';
import { AplicationService } from 'src/app/services/aplication.service';
import { Interview } from 'src/entities/Interview';
import { InterviewService } from 'src/app/services/interview.service';

@Component({
  selector: 'app-interview-form',
  templateUrl: './interview-form.component.html',
  styleUrls: ['./interview-form.component.css'],
})
export class InterviewFormComponent implements OnInit {
  @Input()
  aplication!: Application;
  newInterviewForm!: FormGroup;
  newInterview!: Interview;

  constructor(
    private formBuilder: FormBuilder,
    private interviewService: InterviewService
  ) {}

  ngOnInit(): void {
    this.createForm();
  }

  createForm() {
    this.newInterviewForm = this.formBuilder.group({
      interviewDate: [null, [Validators.required]],
      interviewTime: [null, [Validators.required]],
      direction: [null, [Validators.required]],
      application: this.aplication,
    });
  }

  saveInterview() {
    if (this.newInterviewForm.valid) {
      this.newInterview = this.newInterviewForm.value as Interview;
      console.log('interview: ', this.newInterview);
      this.interviewService.createNewInterview(this.newInterview).subscribe({
        next: (interview) => {
          this.newInterview = interview;
          alert('Interview created');

          console.log('interview: ', this.newInterview);
        },
        error: (error) => {
          console.log('error: ', error.error);
        }
      });
    } else {
      console.log('invalid form');
    }
  }
}
