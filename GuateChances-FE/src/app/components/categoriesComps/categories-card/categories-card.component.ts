import { Component, Input, OnInit } from '@angular/core';
import { Category } from 'src/entities/Category';
import { CategoriesService } from 'src/app/services/categories.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-categories-card',
  templateUrl: './categories-card.component.html',
  styleUrls: ['./categories-card.component.css'],
})
export class CategoriesCardComponent implements OnInit {
  @Input()
  _cat!: Category;
  status: string = 'en Proceso';
  statusMessage: string = '';
  updateMessage: string = '';

  constructor(
    private categoriesService: CategoriesService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.setUpdateMessage();
  }

  setUpdateMessage() {
    if (this._cat.isActive == 1) {
      this.updateMessage = 'Desactivar categoria';
    } else {
      this.updateMessage = 'Activar categoria';
    }
  }

  updateStatus() {
    this.categoriesService.updateCategoryStatus(this._cat).subscribe({
      next: () => {
        alert('Se ha actualizado el estado de la categoria: '+
        this._cat.categoryName);
        this.setUpdateMessage();
        window.location.reload();
      },
      error: () => {
        alert('ocurrio al actualizar la categoria');
      },
    });
  }
}
