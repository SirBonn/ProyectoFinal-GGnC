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

  public createNewInterview(interview: Interview): Observable<Interview> {
    return this.httpCliente.post<Interview>(this.API_URL + '/controller/interviews?action=createInterview', interview);
  }

  public rejectUser(interview: Interview): Observable<Interview> {
    return this.httpCliente.put<Interview>(this.API_URL + '/controller/interviews?action=finaliceInterview&isContract=false', interview);
  }

  public hireUser(interview: Interview): Observable<Interview> {
    return this.httpCliente.put<Interview>(this.API_URL + '/controller/interviews?action=finaliceInterview', interview);
  }

  public getInterviewsByDate(startDate: string, token: string): Observable<Interview[]> {
    return this.httpCliente.get<Interview[]>(this.API_URL + '/controller/interviews?action=getInterviewsByDate&date='+startDate+'&idCode='+token);
  }

  public getInterviewsBySeekerId(seekerId: string): Observable<Interview[]> {
    return this.httpCliente.get<Interview[]>(this.API_URL + '/controller/interviews?action=getUserInterviews&seekerId='+seekerId);
  }

}
