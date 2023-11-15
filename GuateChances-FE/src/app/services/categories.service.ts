import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from 'src/entities/Category';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  readonly API_URL = 'http://localhost:8080/guatechances-api';

  constructor(
    private httpCliente: HttpClient
  ) { }

  public getAllCategories(): Observable<Category[]> {
    return this.httpCliente.get<Category[]>(this.API_URL + '/controller/categories');
  }

  public updateCategoryStatus(category: Category): Observable<Category> {
    return this.httpCliente.put<Category>(this.API_URL + '/controller/categories?catID='+category.idCode, category);
  }



}
