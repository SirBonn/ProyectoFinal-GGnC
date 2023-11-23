import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Offer } from '../../entities/Offer';
import { PlataformPayment } from 'src/entities/PlataformPayment';

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
  public getAllOffersByEmployerId(idCode: string): Observable<Offer[]> {
    return this.httpCliente.get<Offer[]>(this.API_URL + '/controller/offers?action=getOffersByEmployer&offers=all&empCode=' + idCode);
  }

public createOffer(offer: Offer): Observable<Offer> {
    return this.httpCliente.post<Offer>(this.API_URL + '/controller/offers?action=createOffer', offer);
  }

  public getOfferPaymentInfo(): Observable<number> {
    return this.httpCliente.get<number>(this.API_URL + '/controller/offers?action=getOffersPayment');
  }

  public updateOfferPaymentInfo(paymentInfo: number, userId: string): Observable<number> {
    return this.httpCliente.put<number>(this.API_URL + '/controller/offers?action=updateOffersPayment&idCode='+userId, paymentInfo);
  }

  public getPaymentLogs(): Observable<PlataformPayment[]> {
    return this.httpCliente.get<PlataformPayment[]>(this.API_URL + '/controller/offers?action=getPaymentLogs');
  }

  public chekOfferState(): Observable<Offer[]> {
    return this.httpCliente.put<Offer[]>(this.API_URL + '/controller/offers?action=chekOffers', null);
  }

  public getOffersByDate(startDate: string, endDate: string, idCode:string): Observable<Offer[]> {
    return this.httpCliente.get<Offer[]>(this.API_URL + '/controller/offers?action=getOffersByDate&start=' + startDate + '&end=' + endDate+ '&empCode=' + idCode);
  }

  public getTotalPayments(idCode:string): Observable<number> {
    return this.httpCliente.get<number>(this.API_URL + '/controller/offers?action=getTotalPayments&idCode=' + idCode);
  }
}
