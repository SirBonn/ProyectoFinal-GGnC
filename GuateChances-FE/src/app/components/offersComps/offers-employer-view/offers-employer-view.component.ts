import { Component, Input } from '@angular/core';
import { Employer } from 'src/entities/Employer';
import { Offer } from 'src/entities/Offer';


@Component({
  selector: 'app-offers-employer-view',
  templateUrl: './offers-employer-view.component.html',
  styleUrls: ['./offers-employer-view.component.css'],
})
export class OffersEmployerViewComponent{
  @Input()
  _offer!: Offer;
  @Input()
  employer!: Employer;


}
