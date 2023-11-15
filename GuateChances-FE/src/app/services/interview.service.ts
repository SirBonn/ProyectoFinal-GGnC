import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Interview } from 'src/entities/Interview';

@Injectable({
  providedIn: 'root',
})
export class InterviewService {

  constructor(
    private httpCliente: HttpClient
  ) {}

  readonly API_URL = 'http://localhost:8080/guatechances-api';

  public getAllInterviews(appCode: string): Observable<Interview> {
    return this.httpCliente.get<Interview>(this.API_URL + '/controller/interviews?action=getApplicationInterview&appCode='+appCode);
  }

  public getInterviewsByEmployerId(employerId: string): Observable<Interview[]> {
    return this.httpCliente.get<Interview[]>(this.API_URL + '/controller/interviews?action=getEmployerInterviews&empCode='+employerId);
  }
  //
}
