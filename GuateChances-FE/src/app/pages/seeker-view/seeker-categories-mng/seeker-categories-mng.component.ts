import { Component, OnInit } from '@angular/core';
import { Category } from 'src/entities/Category';
import { SeekersService } from 'src/app/services/seekers.service';
import { TokenService } from 'src/app/services/token.service';
import { User } from 'src/entities/User';

@Component({
  selector: 'app-seeker-categories-mng',
  templateUrl: './seeker-categories-mng.component.html',
  styleUrls: ['./seeker-categories-mng.component.css'],
})
export class SeekerCategoriesMngComponent implements OnInit {
  myCategories!: Category[];
  allCategories!: Category[];
  seeker!: User;

  constructor(
    private seekersService: SeekersService,
    private tokenService: TokenService
  ) {
    this.getCategoryList();
  }

  ngOnInit(): void {
    this.getSeeker();
  }

  getSeeker() {
    this.seekersService.getSeeker(this.tokenService.getToken()!).subscribe({
      next: (seeker: User) => {
        console.log('seeker: ', seeker);
        this.seeker = seeker;
      },
      error: () => {
        console.log('error al obtener el usuario');
      },
    });
  }

  getCategoryList() {
    this.seekersService
      .getCategoriesList(this.tokenService.getToken()!)
      .subscribe({
        next: (categories: Category[]) => {
          this.myCategories = categories;
          console.log(categories);
        },
      });

    this.seekersService.getCategoriesDiff(this.tokenService.getToken()!).subscribe({
      next: (categories: Category[]) => {
        this.allCategories = categories;
        console.log(categories);
      },
    });
  }

}
