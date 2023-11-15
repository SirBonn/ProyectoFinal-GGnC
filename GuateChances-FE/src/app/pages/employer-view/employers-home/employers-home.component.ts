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
  offers!: Offer[];
  applications!: Application[];
  employer!: Employer;

  constructor(
    private offerService: OfferService,
    private employerService: EmployersService,
    private applicationService: AplicationService,
    private tokenService: TokenService,
  ) {
    this.setOffers();
  }

  ngOnInit(): void {
    this.setUser();
  }

  setUser() {
    this.employerService.getEmployerByCui(this.tokenService.getToken()!).subscribe({
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
    this.offerService.getOffersByEmployerId(this.tokenService.getToken()!).subscribe({
      next: (offers: Offer[]) => {
        console.log('offers: ', offers);
        this.offers = offers;
      },
      error: () => {
        console.log('error al obtener las ofertas');
      },
    });
  }

}
