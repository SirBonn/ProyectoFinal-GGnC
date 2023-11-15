import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Employer } from 'src/entities/Employer';
import { EmployersService } from 'src/app/services/employers.service';
import { Offer } from 'src/entities/Offer';
import { OfferService } from 'src/app/services/offer.service';

@Component({
  selector: 'app-create-offer-form',
  templateUrl: './create-offer-form.component.html',
  styleUrls: ['./create-offer-form.component.css'],
})
export class CreateOfferFormComponent implements OnInit {
  newOfferForm!: FormGroup;
  @Input()
  employer!: Employer;
  IsEdditing: boolean = false;
  newOffer!: Offer;

  constructor(
    private formBuilder: FormBuilder,
    private offerService: OfferService
  ) {}

  ngOnInit(): void {
    this.newOfferForm = this.formBuilder.group({
      offerName: [null, [Validators.required, Validators.maxLength(50)]],
      offerDesc: [null, [Validators.required, Validators.maxLength(500)]],
      category: [null, [Validators.required]],
      expireDate: [null, [Validators.required]],
      employer: this.employer,
      payment: [null, [Validators.required]],
      direction: [null, [Validators.required, Validators.maxLength(50)]],
      modality: [null, [Validators.required]],
      details: [null, [Validators.required, Validators.maxLength(500)]],
    });
  }

  submit() {
    if (this.newOfferForm.valid) {
      this.newOffer = this.newOfferForm.value as Offer;
      this.offerService.createOffer(this.newOffer).subscribe({
        next: (created: Offer) => {
          console.log('created: ', created);
          alert('Oferta creada exitosamente');
          window.location.reload();
        },
        error: (error) => {
          console.log('error: ', error.error);
        },
      });
    }
  }
}
