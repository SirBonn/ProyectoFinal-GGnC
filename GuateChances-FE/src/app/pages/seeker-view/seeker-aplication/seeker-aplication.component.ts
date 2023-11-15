import { Component, OnInit } from '@angular/core';
import { Application } from 'src/entities/Application';
import { AplicationService } from 'src/app/services/aplication.service';
import { TokenService } from 'src/app/services/token.service';
import { User } from 'src/entities/User';

@Component({
  selector: 'app-seeker-aplication',
  templateUrl: './seeker-aplication.component.html',
  styleUrls: ['./seeker-aplication.component.css'],
})
export class SeekerAplicationComponent implements OnInit {
  myApplications!: Application[];
  seeker!: User;

  constructor(
    private aplicationService: AplicationService,
    private tokenService: TokenService
  ) {
    this.getApplicationList();
  }

  ngOnInit(): void {}

  getApplicationList() {
    this.aplicationService
      .getAllApplications(this.tokenService.getToken()!)
      .subscribe({
        next: (applications: Application[]) => {
          this.myApplications = applications;
          console.log(applications);
        },
      });
  }
}
