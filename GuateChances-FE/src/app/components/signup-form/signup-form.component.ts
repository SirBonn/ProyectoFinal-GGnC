import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { User } from '../../../entities/User';
import { UserService } from '../../services/user.service';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.css'],
})
export class SignupFormComponent implements OnInit {
  userForm!: FormGroup;
  user!: User;
  saved: boolean;
  status: string = 'init';
  errorMessage: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private loginService: LoginService,
    private router: Router
  ) {
    this.saved = false;
  }

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      idCode: this.generateUniqueId(),
      forename: [
        null,
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(50),
        ],
      ],
      direction: [null, [Validators.minLength(5), Validators.maxLength(50)]],
      username: [
        null,
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(50),
        ],
      ],
      password: [null, [Validators.required, Validators.minLength(8)]],
      noCUI: [null, [Validators.minLength(13), Validators.maxLength(13)]],
      email: [null, [Validators.required, Validators.maxLength(50)]],
      birthdate: [null, [Validators.required]],
      usertype: [2],
    });
  }

  generateUniqueId(): string {
    const timestamp = Date.now().toString(36); // Convierte la marca de tiempo en base 36
    const randomNum = Math.random().toString(36).substr(2, 5); // Genera un número aleatorio y lo convierte en base 36
    return timestamp + randomNum;
  }

  submit() {
    if (this.userForm.valid) {
      this.user = this.userForm.value as User;
      this.userService.createUser(this.user).subscribe({
        next: (created: User) => {
          console.log('created: ', created);
          this.doLogin();
        },
        error: (error) => {
          this.status = 'error';
          console.log('error: ', error.error.error);
          if(error.error.error.includes('1062')){
            this.errorMessage = 'tu CUI o nomrbe de usuario ya existe en la base de datos';
          } else {
            this.errorMessage = 'verifica tus credenciales y vuelve a intentar';
          }

        },
      });
    }
  }

  doLogin() {
    if (this.userForm.valid) {
      this.status = 'loading';
      const { username, password } = this.userForm.value;
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
      this.userForm.markAllAsTouched;
    }
  }
}
