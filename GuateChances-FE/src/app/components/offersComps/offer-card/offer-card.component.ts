import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Offer } from 'src/entities/Offer';

@Component({
  selector: 'app-offer-card',
  templateUrl: './offer-card.component.html',
  styleUrls: ['./offer-card.component.css'],
})
export class OfferCardComponent {
  @Input()
  _offer!: Offer;
  @Output() getEditableOffer = new EventEmitter<Offer>();

  sendEditableOffer(){
    this.getEditableOffer.emit(this._offer);
  }



}


