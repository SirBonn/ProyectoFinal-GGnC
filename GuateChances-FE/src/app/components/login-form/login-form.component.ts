import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { User } from '../../../entities/User';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
})
export class LoginFormComponent {
  status: string = 'init';
  loginForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: [
        null,
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(50),
        ],
      ],
      password: [null, [Validators.required, Validators.minLength(8)]],
    });
  }

  doLogin() {
    if (this.loginForm.valid) {
      this.status = 'loading';
      const { username, password } = this.loginForm.value;
      this.loginService.login(username, password).subscribe({
        next: () => {
          this.status = 'success';
          this.router.navigate(['/view']);
        },
        error: () => {
          this.status = 'error';
        },
      });
    } else {
      this.loginForm.markAllAsTouched;
    }
  }
}
