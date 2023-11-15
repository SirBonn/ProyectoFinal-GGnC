import { Component, OnInit } from '@angular/core';
import { Offer } from 'src/entities/Offer';
import { OfferService } from 'src/app/services/offer.service';
import { Application } from 'src/entities/Application';
import { AplicationService } from 'src/app/services/aplication.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-seekers-selection-page',
  templateUrl: './seekers-selection-page.component.html',
  styleUrls: ['./seekers-selection-page.component.css'],
})
export class SeekersSelectionPageComponent implements OnInit {
  offers!: Offer[];
  applications!: Application[];
  viewApplications:boolean = false;
  offerSelected!: Offer;

  constructor(
    private offerService: OfferService,
    private applicationService: AplicationService,
    private tokenService: TokenService
  ) {
    this.setOffers();
  }

  ngOnInit(): void {}

  setOffers() {
    this.offerService
      .getOffersByEmployerId(this.tokenService.getToken()!)
      .subscribe({
        next: (offers: Offer[]) => {
          console.log('offers: ', offers);
          this.offers = offers;
        },
        error: () => {
          console.log('error al obtener las ofertas');
        },
      });
  }

  setOfferToViewApplications(offer: Offer) {
    this.offerSelected = offer;
    this.viewApplications = true;
    this.getApplications(offer.idCode);
  }

  getApplications(offerId: number) {
    this.applicationService.getApplicationsByOfferId(offerId).subscribe({
      next: (applications: Application[]) => {
        console.log('applications: ', applications);
        this.applications = applications;
      },
      error: (error) => {
        console.log('error al obtener las aplicaciones', error.error);
      },
    });
  }
}
