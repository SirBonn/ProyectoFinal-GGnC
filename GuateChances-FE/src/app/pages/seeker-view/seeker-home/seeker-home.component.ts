import { Component, OnInit } from '@angular/core';
import { SeekersService } from 'src/app/services/seekers.service';
import { TokenService } from 'src/app/services/token.service';
import { User } from 'src/entities/User';
import { Offer } from 'src/entities/Offer';
import { Category } from 'src/entities/Category';

@Component({
  selector: 'app-seeker-home',
  templateUrl: './seeker-home.component.html',
  styleUrls: ['./seeker-home.component.css'],
})
export class SeekerHomeComponent implements OnInit {
  seeker!: User;
  selectedFile!: File;
  errorMessage: string = '';
  fileStatus: String = 'enEspera';
  isPDFfileUploaded: boolean = true;
  OffersByCategories!: Offer[];
  myCategories!: Category[];

  constructor(
    private seekersService: SeekersService,
    private tokenService: TokenService
  ) {
    this.setOffers();
    this.setMyCategories();
  }

  ngOnInit(): void {
    this.setUser();
  }

  setUser() {
    this.seekersService.getSeeker(this.tokenService.getToken()!).subscribe({
      next: (seeker: User) => {
        console.log('seeker: ', seeker);
        this.seeker = seeker;
        this.isUploaded();
      },
      error: () => {
        console.log('error al obtener el usuario');
      },
    });
  }

  isUploaded() {
    this.seekersService.isPDFfileUploaded(this.seeker.idCode).subscribe({
      next: (isUploaded: boolean) => {
        console.log('file status:', isUploaded);
        this.isPDFfileUploaded = isUploaded;
      },
      error: () => {
        console.log('error al obtener el usuario');
      },
    });
  }

setMyCategories(){
  this.seekersService
      .getCategoriesList(this.tokenService.getToken()!)
      .subscribe({
        next: (categories: Category[]) => {
          this.myCategories = categories;
          console.log(categories);
        },
      });
}

  setOffers() {
    this.seekersService.getOffersByCategories(this.tokenService.getToken()!).subscribe({
      next: (offers: Offer[]) => {
        console.log('offers: ', offers);
        this.OffersByCategories = offers;
      },
      error: (error) => {
        console.log('error al obtener el usuario', error.error);
      },
    });
  }
}
