import { Component, Input, OnInit } from '@angular/core';
import { Interview } from 'src/entities/Interview';

@Component({
  selector: 'app-interview-employer-view',
  templateUrl: './interview-employer-view.component.html',
  styleUrls: ['./interview-employer-view.component.css']
})
export class InterviewEmployerViewComponent implements OnInit{

  @Input()
  _interview!: Interview;

  constructor() {}

  ngOnInit(): void {}

}
