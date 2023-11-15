import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SeekersService } from 'src/app/services/seekers.service';
import { OfferService } from 'src/app/services/offer.service';
import { Offer } from 'src/entities/Offer';
import { User } from 'src/entities/User';
import { Application } from 'src/entities/Application';

@Component({
  selector: 'app-application-form',
  templateUrl: './application-form.component.html',
  styleUrls: ['./application-form.component.css'],
})
export class ApplicationFormComponent implements OnInit {
  @Input()
  offer!: Offer;
  @Input()
  seeker!: User;
  havePDF: boolean = false;
  newApplication!: Application;
  applicationForm!: FormGroup;

  constructor(
    private seekersService: SeekersService,
    private offerService: OfferService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.applicationForm = this.formBuilder.group({
      idCode: null,
      seeker: [this.seeker],
      offer: [this.offer],
      state: [0],
      seekerMessage: [null, [Validators.maxLength(500)]]
    });
  }

  submit() {
    if (this.applicationForm.valid) {
      this.newApplication = this.applicationForm.value as Application;
      console.log('form: ', this.applicationForm.value);
      this.seekersService.createApplication(this.newApplication).subscribe({
        next: (application: Application) => {
          console.log('application: ', application);
          window.location.reload();
        },
        error: (error) => {
          console.log('error al crear la aplicación: ', error.error);
        },
      });
    } else {
      console.log('form invalido');
    }
  }
}
