import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Offer } from '../../entities/Offer';

@Injectable({
  providedIn: 'root'
})
export class OfferService {

  readonly API_URL = 'http://localhost:8080/guatechances-api';

  constructor(
    private httpCliente: HttpClient
  ) { }

  public getAllOffers(): Observable<Offer[]> {
    return this.httpCliente.get<Offer[]>(this.API_URL + '/controller/offers');
  }


  public getOffersByEmployerId(idCode: string): Observable<Offer[]> {
    return this.httpCliente.get<Offer[]>(this.API_URL + '/controller/offers?action=getOffersByEmployer&empCode=' + idCode);
  }

public createOffer(offer: Offer): Observable<Offer> {
    return this.httpCliente.post<Offer>(this.API_URL + '/controller/offers?action=createOffer', offer);
  }
}
