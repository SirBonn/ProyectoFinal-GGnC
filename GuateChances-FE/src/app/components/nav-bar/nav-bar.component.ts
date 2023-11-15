import { Component } from '@angular/core';
import { User } from 'src/entities/User';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {

  user!: User;
  username!: string;

  constructor(
    private loginService: LoginService,
    private tokenService: TokenService,
    private router: Router
  ) {
    this.loginService.setUser(this.tokenService.getToken()!).subscribe({
      next: (userLoged: User) => {
        this.user = userLoged;
        this.username = userLoged.username;
      },
      error:() => {
        console.log("error al obtener el usuario");
      }
    });
    this.user = this.loginService.user!;
  }

  logout(e: Event): void {
    e.preventDefault
    this.loginService.logout();
    this.router.navigate(['/']);
  }

}
