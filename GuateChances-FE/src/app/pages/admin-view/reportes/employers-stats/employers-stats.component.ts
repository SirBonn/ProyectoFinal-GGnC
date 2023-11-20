import { Component, OnInit } from '@angular/core';
import { EmployersService } from 'src/app/services/employers.service';
import { Employer } from 'src/entities/Employer';
import { EmployerByOffers } from 'src/entities/EmployerByOffers';
import { OfferService } from 'src/app/services/offer.service';
import { Offer } from 'src/entities/Offer';

@Component({
  selector: 'app-employers-stats',
  templateUrl: './employers-stats.component.html',
  styleUrls: ['./employers-stats.component.css'],
})
export class EmployersStatsComponent implements OnInit{
  startDate!: string;
  endDate!: string;
  topByPays!: Employer[];
  topByOffers!: EmployerByOffers[];
  offersByUser!: Offer[];
  showOffers: boolean = false;

  constructor(
    private employersService: EmployersService,
    private offerService: OfferService
  ) {}

  ngOnInit(): void {
    this.employersService.getTopEmployersByOffers().subscribe({
      next: (employersByOffer) => {
        this.topByOffers = employersByOffer;
        console.log(employersByOffer);
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  sendDates(): void {

    this.employersService.getTopEmployersByPays(this.startDate, this.endDate).subscribe({
      next: (employers) => {
        this.topByPays = employers;
        console.log(employers);
      },
      error: (error) => {
        console.log(error);
      },
    });

  }
  showOffersByUser(employer: Employer){

    this.offerService.getAllOffersByEmployerId(employer.idCode).subscribe({
      next: (offers) => {
        this.offersByUser = offers;
        console.log(offers);
        this.showOffers = true;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  hideOffers(){
    this.showOffers = false;
  }

}
