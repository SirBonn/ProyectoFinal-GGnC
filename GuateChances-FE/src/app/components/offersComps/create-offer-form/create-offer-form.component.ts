import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Employer } from 'src/entities/Employer';
import { EmployersService } from 'src/app/services/employers.service';
import { CategoriesService } from 'src/app/services/categories.service';
import { Offer } from 'src/entities/Offer';
import { OfferService } from 'src/app/services/offer.service';
import { Category } from 'src/entities/Category';

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
  categories!: Category[];
  newOffer!: Offer;

  constructor(
    private formBuilder: FormBuilder,
    private offerService: OfferService,
    private categoryService: CategoriesService
  ) {}

  ngOnInit(): void {
    this.loadCategories();
    this.createForm();
  }

  createForm() {
    this.newOfferForm = this.formBuilder.group({
      offerName: [null, [Validators.required, Validators.maxLength(50)]],
      offerDesc: [null, [Validators.required, Validators.maxLength(500)]],
      category: [[Validators.required]],
      expireDate: [null, [Validators.required]],
      employer: this.employer,
      payment: [null, [Validators.required]],
      direction: [null, [Validators.required, Validators.maxLength(50)]],
      modality: [0, [Validators.required]],
      details: [null, [Validators.required, Validators.maxLength(500)]],
    });
  }

  loadCategories() {
    this.categoryService.getAllCategories().subscribe({
      next: (categories) => {
        this.categories = categories;
        console.log('categproes: ', this.categories);
      },
      error: (error) => {
        console.log('error: ', error.error);
      },
    });
  }

  submit() {
    if (this.newOfferForm.valid) {
      this.newOffer = this.newOfferForm.value as Offer;
      this.newOffer.employer = this.employer;
      console.log('created: ', this.newOffer);
      this.offerService.createOffer(this.newOffer).subscribe({
        next: (created: Offer) => {
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
