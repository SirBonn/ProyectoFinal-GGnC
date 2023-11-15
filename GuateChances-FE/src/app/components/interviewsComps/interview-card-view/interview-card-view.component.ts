import { Component, Input, OnInit } from '@angular/core';
import { Interview } from 'src/entities/Interview';

@Component({
  selector: 'app-interview-card-view',
  templateUrl: './interview-card-view.component.html',
  styleUrls: ['./interview-card-view.component.css'],
})
export class InterviewCardViewComponent implements OnInit {
  @Input()
  _interview!: Interview;

  constructor() {}

  ngOnInit(): void {}
}
