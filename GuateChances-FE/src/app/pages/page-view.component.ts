import { Component, OnInit } from '@angular/core';
import { User } from '../../entities/User';
import { LoginService } from '../services/login.service';
import { TokenService } from '../services/token.service';
import { Router } from '@angular/router';
import { Employer } from 'src/entities/Employer';
import { Seeker } from 'src/entities/Seeker';

@Component({
  selector: 'app-page-view',
  templateUrl: './page-view.component.html',
  styleUrls: ['./page-view.component.css'],
})
export class PageViewComponent implements OnInit {
  user!: User;
  userType!: number;
  empployer!: Employer;
  seeker!: Seeker;


  constructor(
    private loginService: LoginService,
    private tokenService: TokenService,
    private router: Router
  ) {

  }

  ngOnInit(): void {
    this.loginService.setUser(this.tokenService.getToken()!).subscribe({
      next: (userLoged: User) => {
        this.user = userLoged;
        this.userType = this.user.usertype;
        this.router.navigate([this.redirectByUser()]);
      },
      error: () => {
        console.log('error al obtener el usuario');
      },
    });
    this.user = this.loginService.user!;
  }

  redirectByUser(): string {
    if (this.user.usertype == 0) {
      return '/view/admin';
    } else if (this.user.usertype == 1) {
      return '/view/employer';
    } else if (this.user.usertype == 2) {
      return '/view/seeker';
    } else {
      return '/';
    }
  }
}
