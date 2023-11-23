import { Component, OnInit } from '@angular/core';
import { Offer } from 'src/entities/Offer';
import { OfferService } from 'src/app/services/offer.service';
import { TokenService } from 'src/app/services/token.service';
@Component({
  selector: 'app-my-offers-view',
  templateUrl: './my-offers-view.component.html',
  styleUrls: ['./my-offers-view.component.css'],
})
export class MyOffersViewComponent implements OnInit {
  offersList: Offer[] = [];
  offerEditable!: Offer;
  _isActiveViewOFR: boolean = true;

  constructor(
    private offerService: OfferService,
    private tokenService: TokenService
  ) {}

  ngOnInit(): void {
    this.offerService.getAllOffersByEmployerId(this.tokenService.getToken()!).subscribe({
      next: (offers: Offer[]) => {
        this.offersList = offers;
      },
    });
  }

  setEditableOffer(Offer: Offer) {
    this.offerEditable = Offer;
    console.log(this.offerEditable);
    this._isActiveViewOFR = true;
  }

  setIsActiveView(isActiveView: boolean, mode: number) {
    if (mode == 1) {
      this._isActiveViewOFR = isActiveView;
    }
  }
}
