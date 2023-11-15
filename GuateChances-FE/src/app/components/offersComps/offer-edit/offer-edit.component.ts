import { Component, Input, OnInit } from '@angular/core';
import { Offer } from 'src/entities/Offer';
import { User } from 'src/entities/User';

@Component({
  selector: 'app-offer-edit',
  templateUrl: './offer-edit.component.html',
  styleUrls: ['./offer-edit.component.css'],
})
export class OfferEditComponent implements OnInit{
  @Input()
  _offer!: Offer;
  isEditable: boolean = true;
  state: string = '';
  _modality: string = '';

  ngOnInit(): void {
    this.setOfferState();
    this.setModality();
  }

  setOfferState(){
    if (this._offer.offerState == 0) {
      this.state = 'Activa';
    } else if (this._offer.offerState == 1) {
      this.state = 'En espera de seleccion';
    } else if (this._offer.offerState == 2) {
      this.state = 'Finalizada';
    }
  }

  setModality(){
    if (this._offer.modality == 0) {
      this._modality = 'Presencial';
    } else if (this._offer.modality == 1) {
      this._modality = 'Remoto';
    }
  }

  editOffer() {
    this.isEditable = !this.isEditable;
  }

  closeOfferEdit(){
  }

  saveOfferChanges(){}
}
