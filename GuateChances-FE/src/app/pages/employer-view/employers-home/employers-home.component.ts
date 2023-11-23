import { Component, OnInit } from '@angular/core';
import { Offer } from 'src/entities/Offer';
import { OfferService } from 'src/app/services/offer.service';
import { Application } from 'src/entities/Application';
import { AplicationService } from 'src/app/services/aplication.service';
import { TokenService } from 'src/app/services/token.service';
import { Employer } from 'src/entities/Employer';
import { EmployersService } from 'src/app/services/employers.service';

@Component({
  selector: 'app-employers-home',
  templateUrl: './employers-home.component.html',
  styleUrls: ['./employers-home.component.css'],
})
export class EmployersHomeComponent implements OnInit {
  allOffers!: Offer[];
  activeOffers!: Offer[];
  closeOffers!: Offer[];
  interviewOffers!: Offer[];

  applications!: Application[];
  employer!: Employer;

  constructor(
    private offerService: OfferService,
    private employerService: EmployersService,
    private applicationService: AplicationService,
    private tokenService: TokenService
  ) {
    this.setOffers();
  }

  ngOnInit(): void {
    this.setUser();
  }

  setUser() {
    this.employerService
      .getEmployerByCui(this.tokenService.getToken()!)
      .subscribe({
        next: (_emp: Employer) => {
          this.employer = _emp;
          console.log('employer: ', this.employer);
        },
        error: () => {
          console.log('error al obtener el empleador');
        },
      });
  }

  setOffers() {
    this.offerService
      .getAllOffersByEmployerId(this.tokenService.getToken()!)
      .subscribe({
        next: (offers: Offer[]) => {
          console.log('offers: ', offers);
          this.allOffers = offers;
          this.activeOffers = offers.filter((offer) => offer.offerState == 0);
          this.closeOffers = offers.filter((offer) => offer.offerState == 2);
          this.interviewOffers = offers.filter((offer) => offer.offerState == 1);
          console.log('activeOffers: ', this.activeOffers);
          console.log('closeOffers: ', this.closeOffers);
          console.log('interviewOffers: ', this.interviewOffers);

        },
        error: () => {
          console.log('error al obtener las ofertas');
        },
      });
  }
}
