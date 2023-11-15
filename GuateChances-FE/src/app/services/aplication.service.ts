import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Application} from 'src/entities/Application';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/entities/User';

@Injectable({
  providedIn: 'root'
})
export class AplicationService {
  readonly API_URL = 'http://localhost:8080/guatechances-api';

  constructor(
    private httpCliente: HttpClient
  ) { }

  public getAllApplications(userCode:String): Observable<Application[]> {
    return this.httpCliente.get<Application[]>(this.API_URL + '/controller/application?action=getApplications&userCode='+userCode);
  }


  public deleteApplication(application:Application): Observable<Application[]> {
    return this.httpCliente.delete<Application[]>(this.API_URL + '/controller/application?idApplication='+application.idCode);
  }

  public getApplicationsByOfferId(offerId: number): Observable<Application[]> {
    return this.httpCliente.get<Application[]>(this.API_URL + '/controller/application?action=getApplicationsByOffer&offerId='+offerId);
  }

}
