import { Component, Input, OnInit } from '@angular/core';
import { Offer } from 'src/entities/Offer';
import { User } from 'src/entities/User';

@Component({
  selector: 'app-offers-seeker-view',
  templateUrl: './offers-seeker-view.component.html',
  styleUrls: ['./offers-seeker-view.component.css']
})
export class OffersSeekerViewComponent implements OnInit{


  @Input()
  offer!: Offer;
  @Input()
  _seeker!: User;
  viewForm: boolean = false;

  constructor() { }

  ngOnInit(): void {

  }

  applicateToOffer(){
    this.viewForm = !this.viewForm;
  }



}
