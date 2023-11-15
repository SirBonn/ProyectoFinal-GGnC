import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { User } from '../../entities/User';
import { TokenService } from './token.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  user?: User;

  constructor(private http: HttpClient, private tokenService: TokenService) {}

  login(username: string, password: string) {
    return this.http
      .post<User>('http://localhost:8080/guatechances-api/login', {
        username,
        password,
      })
      .pipe(
        tap((response) => {
          this.tokenService.saveToken(response.idCode);
        })
      );
  }

  setUser(idCode: string): Observable<User> {
    return this.http.get<User>('http://localhost:8080/guatechances-api/login?idCode=' +idCode);
  }

  logout(): void {
    this.tokenService.removeToken();
  }


}
