import { Component, Input, OnInit } from '@angular/core';
import { Application } from 'src/entities/Application';

@Component({
  selector: 'app-application-simple-card',
  templateUrl: './application-simple-card.component.html',
  styleUrls: ['./application-simple-card.component.css'],
})
export class ApplicationSimpleCardComponent implements OnInit {
  @Input()
  _application!: Application;
  appState!: string;

  ngOnInit(): void {
    if (this._application.state == 0) {
      this.appState = 'Pendiente';
    } else if (this._application.state == 1) {
      this.appState = 'Seleccionado';
    } else if (this._application.state == 2) {
      this.appState = 'Rechazado';
    }
  }

  seleccionarUsuario(userCode: string) {
    console.log('seleccionando usuario: ', userCode);
  }

  rechazar(userCode: string) {
    console.log('rechazando usuario: ', userCode);
  }
}
