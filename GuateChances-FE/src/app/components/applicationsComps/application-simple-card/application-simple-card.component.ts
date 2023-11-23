import { Component, Input, OnInit } from '@angular/core';
import { Application } from 'src/entities/Application';
import { Seeker } from 'src/entities/Seeker';
import { AplicationService } from 'src/app/services/aplication.service';

@Component({
  selector: 'app-application-simple-card',
  templateUrl: './application-simple-card.component.html',
  styleUrls: ['./application-simple-card.component.css'],
})
export class ApplicationSimpleCardComponent implements OnInit {
  @Input()
  _application!: Application;
  appState!: string;
  stateInterview: string = 'waiting';
  userSelected!: Seeker;

  constructor(private applicationService: AplicationService) {}

  ngOnInit(): void {
    if (this._application.state == 0) {
      this.appState = 'Pendiente';
    } else if (this._application.state == 1) {
      this.appState = 'Seleccionado';
    } else if (this._application.state == 2) {
      this.appState = 'Rechazado';
    }
  }

  seleccionarUsuario() {
    this.stateInterview = 'selected';
    console.log('stateInterview: ', this.stateInterview);
  }

  rechazar() {
    this._application.state = 2;
    this.applicationService.rejectUser(this._application).subscribe({
      next: (data) => {
        console.log('data', data);
      },
      error: (error) => {
        console.log('error', error);
      },
    });
  }
}
