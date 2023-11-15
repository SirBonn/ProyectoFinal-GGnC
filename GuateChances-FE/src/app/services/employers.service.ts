import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employer } from 'src/entities/Employer';
import { Offer } from 'src/entities/Offer';

@Injectable({
  providedIn: 'root',
})
export class EmployersService {


  readonly API_URL = 'http://localhost:8080/guatechances-api';

  constructor(private httpCliente: HttpClient) {}

  public getEmployerByCui(idCode: string): Observable<Employer> {
    return this.httpCliente.get<Employer>(this.API_URL + '/controller/usr/employers?idCode='+idCode);
  }

  public updateEmployer(employer: Employer): Observable<Employer> {
    return this.httpCliente.put<Employer>(this.API_URL + '/controller/usr/employers', employer);
  }

}
