import { Component, Input, OnInit } from '@angular/core';
import { Application } from 'src/entities/Application';
import { AplicationService } from 'src/app/services/aplication.service';
import { Interview } from 'src/entities/Interview';
import { InterviewService } from 'src/app/services/interview.service';

@Component({
  selector: 'app-application-card',
  templateUrl: './application-card.component.html',
  styleUrls: ['./application-card.component.css'],
})
export class ApplicationCardComponent implements OnInit {
  @Input()
  _application!: Application;
  appState!: string;
  interview!: Interview;
  showEmployerInfo: boolean = false;
  showOfferInfo: boolean = false;
  isSelected: boolean = false;
  showinterviewInfo: boolean = false;

  constructor(
    private aplicationService: AplicationService,
    private interviewService: InterviewService
    ) {
    }

  ngOnInit(): void {
    if (this._application.state == 0) {
      this.appState = 'Pendiente';
    } else if (this._application.state == 1) {
      this.isSelected = true;
      this.getInterview();
      this.appState = 'Seleccionado';
    } else if (this._application.state == 2) {
      this.appState = 'Rechazado';
    }
  }

  getInterview() {
    this.interviewService
      .getAllInterviews(this._application.idCode.toString())
      .subscribe({
        next: (interview) => {
          this.interview = interview;
          console.log("interview getted",this.interview);
        },
        error: (error) => {
          console.log(error.error);
        },
      });
  }

  deleteApplication() {
    if (this.appState == 'Pendiente') {
      this.aplicationService.deleteApplication(this._application).subscribe({
        next: () => {
          alert('Solicitud eliminada');
        },
        error: (error) => {
          console.log(error.error);
        },
      });
    } else {
      alert('No se puede eliminar esta solicitud');
    }
  }

  showEmployer() {
    if (this.showOfferInfo) {
      this.showOffer();
    }
    this.showEmployerInfo = !this.showEmployerInfo;
  }

  showInterview() {
    if (this.showOfferInfo) {
      this.showOffer();
    }
    if (this.showEmployerInfo) {
      this.showEmployer();
    }


    this.showinterviewInfo = !this.showinterviewInfo;
  }

  showOffer() {
    if (this.showEmployerInfo) {
      this.showEmployer();
    }
    this.showOfferInfo = !this.showOfferInfo;
  }
}
