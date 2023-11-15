import { Component, Input, OnInit } from '@angular/core';
import { Offer } from 'src/entities/Offer';

@Component({
  selector: 'app-offer-simple-card',
  templateUrl: './offer-simple-card.component.html',
  styleUrls: ['./offer-simple-card.component.css']
})
export class OfferSimpleCardComponent implements OnInit{

  @Input()
  _offer!: Offer;
  _modality!: string;

  ngOnInit(): void {
    this.setModality();
  }

  setModality(){
    if (this._offer.modality === 0) {
      this._modality = 'Presencial';
    } else if (this._offer.modality === 1) {
      this._modality = 'Remoto';
    }
  }


}
