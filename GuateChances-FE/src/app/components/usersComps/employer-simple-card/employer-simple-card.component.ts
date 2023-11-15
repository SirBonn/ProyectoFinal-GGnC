import { Component, Input, OnInit } from '@angular/core';
import { Employer } from 'src/entities/Employer';

@Component({
  selector: 'app-employer-simple-card',
  templateUrl: './employer-simple-card.component.html',
  styleUrls: ['./employer-simple-card.component.css']
})
export class EmployerSimpleCardComponent implements OnInit{

  @Input()
  _user!: Employer;
  constructor() { }

  ngOnInit(): void {

  }
}
