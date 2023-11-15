import { Injectable } from '@angular/core';
import { User } from '../../entities/User';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  readonly API_URL = 'http://localhost:8080/guatechances-api';
  readonly API_URL_SIGNIN = this.API_URL + '/signin';

  constructor(private httpCliente: HttpClient) {}

  public createUser(user: User): Observable<User> {
    console.log('creando el usuario', user);
    return this.httpCliente.post<User>(this.API_URL_SIGNIN, user);
  }

  public getUser(idCode: string): Observable<User> {
    return this.httpCliente.get<User>(this.API_URL + '/controller/usr/users?userID='+idCode);
  }

  public getAllUsers(): Observable<User[]> {
    return this.httpCliente.get<User[]>(this.API_URL + '/controller/usr/users');
  }

  public updateStatusUser(user: User): Observable<User> {
    return this.httpCliente.put<User>(this.API_URL + '/controller/usr/users?userID='+user.idCode, user);
  }


}
