import { Component, OnInit } from '@angular/core';
import { PlataformPayment } from 'src/entities/PlataformPayment';
import { OfferService } from 'src/app/services/offer.service';

@Component({
  selector: 'app-payment-log',
  templateUrl: './payment-log.component.html',
  styleUrls: ['./payment-log.component.css'],
})
export class PaymentLogComponent implements OnInit {
  plataformPayments!: PlataformPayment[];
  constructor(private offerService: OfferService) {}

  ngOnInit(): void {
    this.offerService.getPaymentLogs().subscribe({
      next: (logs) => {
        this.plataformPayments = logs;
        console.log(logs);
      },
      error: (error) => {
        console.log(error);
      },
    });
  }
}
