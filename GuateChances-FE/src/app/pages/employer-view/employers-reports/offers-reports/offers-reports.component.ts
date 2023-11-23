import { Component, OnInit } from '@angular/core';
import { Offer } from 'src/entities/Offer';
import { OfferService } from 'src/app/services/offer.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-offers-reports',
  templateUrl: './offers-reports.component.html',
  styleUrls: ['./offers-reports.component.css']
})
export class OffersReportsComponent implements OnInit{
  startDate!: string;
  endDate!: string;
  totalPays: number = 0;
  offersByDate!: Offer[];
  activeOffers!: Offer[];
  selectionOffers!: Offer[];
  finalizatedOffers!: Offer[];
  showAll: boolean = false;
  showOffers: boolean = false;

  constructor(
    private offerService: OfferService,
    private tokenService: TokenService
  ) { }

ngOnInit(): void {
    this.offerService.getTotalPayments(this.tokenService.getToken()!).subscribe(
      {
        next: (result: number) => {
          this.totalPays = result;
        },
        error: (error) => {
          console.error('Error: ' + error);
        }
      }
    );
}

  sendDates(): void {

    this.offerService.getOffersByDate(this.startDate, this.endDate, this.tokenService.getToken()!).subscribe(
      {
        next: (result: Offer[]) => {
          this.offersByDate = result;
          this.activeOffers = result.filter((offer) => offer.offerState == 0);
          this.finalizatedOffers = result.filter((offer) => offer.offerState == 2);
          this.selectionOffers = result.filter((offer) => offer.offerState == 1);
          console.log(this.offersByDate);
          this.showOffers = true;
        },
        error: (error) => {
          console.error('Error fetching offers by date: ' + error);
        }
      }
    );
  }

  getAllOffers(){

    this.offerService.getAllOffersByEmployerId(this.tokenService.getToken()!).subscribe(
      {
        next: (result: Offer[]) => {
          this.offersByDate = result;
          console.log(this.offersByDate);
        },
        error: (error) => {
          console.error('Error: ' + error);
        }
      }
    );
    this.showAll= !this.showAll;
  }

}
