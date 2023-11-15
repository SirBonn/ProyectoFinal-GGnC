import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, NgModel } from '@angular/forms';
import { Employer } from 'src/entities/Employer';
import { EmployersService } from 'src/app/services/employers.service';
import { User } from 'src/entities/User';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css'],
})
export class UserEditComponent implements OnInit {
  @Input()
  _user!: User;
  _userType!: string;
  userForm!: FormGroup;
  _employer!: Employer;
  saved: boolean;
  isEditable: boolean = true;
  userState: string = '';
  updateMessage: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private employersService: EmployersService,
    private userService: UserService
  ) {
    this.saved = false;

    this.userForm = this.formBuilder.group({
      forename: [null, [Validators.required, Validators.maxLength(50)]],
      direction: [null, [Validators.minLength(5), Validators.maxLength(50)]],
      username: [null, [Validators.required, Validators.maxLength(50)]],
      noCUI: [null, [Validators.minLength(13), Validators.maxLength(13)]],
      email: [null, [Validators.required, Validators.maxLength(50)]],
      vision: [null, [Validators.required, Validators.maxLength(50)]],
      mision: [null, [Validators.required, Validators.maxLength(50)]],
    });
  }

  ngOnInit(): void {
    console.log(this._user);
    this.setUserType();
    this.setUser();
    this.setUserState();
    this.setUpdateMessage();
  }

  setUserType() {
    if (this._user.usertype == 0) {
      this._userType = 'Admin';
    } else if (this._user.usertype == 1) {
      this._userType = 'Empleador';
    } else if (this._user.usertype == 2) {
      this._userType = 'Solicitante';
    }
  }

  setUserState() {
    if (this._user.isActive == 0) {
      this.userState = 'Inactivo';
    } else if (this._user.isActive == 1) {
      this.userState = 'Activo';
    }
  }

  setUser() {
    if (this._user.usertype == 1) {
      this.employersService.getEmployerByCui(this._user.idCode).subscribe({
        next: (employer: Employer) => {
          this._employer = employer;
          console.log('obtenido', this._employer);
        },
        error: () => {
          console.log('error al obtener el employer');
        },
      });
    } else if (this._user.usertype == 2) {
    }
  }

  updateUser() {
    if (this._user.usertype == 1) {
      this.employersService.updateEmployer(this._employer).subscribe({
        next: () => {
          alert('Se ha actualizado el usuario');
          window.location.reload();
        },
        error: () => {
          alert('No se ha podido actualizar el usuario');
        },
      });
    } else if (this._user.usertype == 2) {

    }
  }

  setUpdateMessage() {
    if (this._user.isActive == 1) {
      this.updateMessage = 'Desactivar usuario';
    } else {
      this.updateMessage = 'Activar usuario';
    }
  }

  editUser() {
    this.isEditable = !this.isEditable;
  }

  updateStatus() {
    this.userService.updateStatusUser(this._user).subscribe({
      next: () => {
        alert('Se ha actualizado el estado del usuario');
        window.location.reload();
      },
      error: () => {
        alert('No se ha podido actualizar el estado del usuario');
      },
    });
  }
}
