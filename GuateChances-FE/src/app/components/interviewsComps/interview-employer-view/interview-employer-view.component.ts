import { Component, Input } from '@angular/core';
import { Interview } from 'src/entities/Interview';

@Component({
  selector: 'app-interview-employer-view',
  templateUrl: './interview-employer-view.component.html',
  styleUrls: ['./interview-employer-view.component.css'],
})
export class InterviewEmployerViewComponent {
  @Input()
  _interview!: Interview;
  showForm: boolean = false;

  constructor() {}

  showContractForm() {
    this.showForm = this.showForm ? false : true;
  }
}
