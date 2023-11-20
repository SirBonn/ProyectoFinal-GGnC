import { Component, OnInit } from '@angular/core';
import { OfferService } from 'src/app/services/offer.service';
import { TokenService } from 'src/app/services/token.service';
@Component({
  selector: 'app-payment-info',
  templateUrl: './payment-info.component.html',
  styleUrls: ['./payment-info.component.css'],
})
export class PaymentInfoComponent implements OnInit {
  paymentInfo!: number;
  enableChanges!: boolean;
  status: string = 'pending';

  constructor(
    private offerService: OfferService,
    private tokenService: TokenService
  ) {}

  ngOnInit(): void {
    this.offerService.getOfferPaymentInfo().subscribe({
      next: (data) => {
        this.paymentInfo = data;
        console.log(data);
      },
      error: (error) => {
        console.log(error.error.message);
      },
    });
  }

  enableChange() {
    this.enableChanges = !this.enableChanges;
    this.status = 'pending';
  }

  saveChanges() {
    this.offerService
      .updateOfferPaymentInfo(this.paymentInfo, this.tokenService.getToken()!)
      .subscribe({
        next: (data) => {
          console.log(data);
          this.status = 'success';
          this.enableChanges = !this.enableChanges;
        },
        error: (error) => {
          console.log(error.error.message);
        },
      });
  }
}
