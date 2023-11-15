import { Component, OnInit } from '@angular/core';
import { Category } from 'src/entities/Category';
import { CategoriesService } from 'src/app/services/categories.service';

@Component({
  selector: 'app-categories-mng',
  templateUrl: './categories-mng.component.html',
  styleUrls: ['./categories-mng.component.css'],
})
export class CategoriesMngComponent implements OnInit {

  categoriesList: Category[] = [];

  constructor(private categoriesService: CategoriesService) {

  }

  ngOnInit(): void {
    this.categoriesService.getAllCategories().subscribe({
      next: (categories: Category[]) => {
        this.categoriesList = categories;
        console.log(categories);
      }
    });
  }

}
