import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/entities/User';
import { Seeker } from 'src/entities/Seeker';
import { Category } from 'src/entities/Category';
import { Offer } from 'src/entities/Offer';
import { Application } from 'src/entities/Application';

@Injectable({
  providedIn: 'root'
})
export class SeekersService {


  readonly API_URL = 'http://localhost:8080/guatechances-api';

  constructor(private httpCliente: HttpClient) {}

  public getSeeker(idCode: string): Observable<User> {
    return this.httpCliente.get<User>(this.API_URL + '/controller/usr/seekers?idCode='+idCode);
  }

  public updateSeeker(seeker: Seeker): Observable<Seeker> {
    return this.httpCliente.put<Seeker>(this.API_URL + '/controller/usr/seekers', seeker);
  }

  public insertNewCategory(idCode: string, category: Category): Observable<Category> {
    return this.httpCliente.post<Category>(this.API_URL + '/controller/usr/seekers?action=addCategory&idCode='+idCode, category);
  }

  public deleteCategory(idCode: string, category: Category): Observable<Category> {
    return this.httpCliente.post<Category>(this.API_URL + '/controller/usr/seekers?action=removeCategory&idCode='+idCode, category);
  }

  public isPDFfileUploaded(idCode: string): Observable<boolean> {
    return this.httpCliente.get<boolean>(this.API_URL + '/controller/usr/seekers?action=isPDFfileUploaded&idCode='+idCode);
  }

  public getCategoriesList(idCode: string): Observable<Category[]> {
    return this.httpCliente.get<Category[]>(this.API_URL + '/controller/usr/seekers?action=getCategoriesList&idCode='+idCode);
  }

  public getCategoriesDiff(idCode: string): Observable<Category[]> {
    return this.httpCliente.get<Category[]>(this.API_URL + '/controller/usr/seekers?action=getDiffCategoriesList&idCode='+idCode);
  }

  public getOffersByCategories(idCode: string): Observable<Offer[]> {
    return this.httpCliente.get<Offer[]>(this.API_URL + '/controller/usr/seekers?action=getOffersByCategories&idCode='+idCode);
  }

  public createApplication(application: Application): Observable<Application> {
    console.log('creando la aplicación', application);
    return this.httpCliente.post<Application>(this.API_URL + '/controller/application?action=addApplication', application);
  }

}
