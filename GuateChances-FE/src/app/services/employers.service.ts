import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employer } from 'src/entities/Employer';
import { EmployerByOffers } from 'src/entities/EmployerByOffers';

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

  public getTopEmployersByOffers(): Observable<EmployerByOffers[]> {
    return this.httpCliente.get<EmployerByOffers[]>(this.API_URL + '/controller/usr/employers?action=getTopEmployersByOffers');
  }

  public getTopEmployersByPays(startDate:string, endDate:string): Observable<Employer[]> {
    console.log(startDate + "," + endDate);
    return this.httpCliente.get<Employer[]>(this.API_URL + '/controller/usr/employers?action=getTopEmployersByPays&startDate='+startDate+'&endDate='+endDate);
  }

}
