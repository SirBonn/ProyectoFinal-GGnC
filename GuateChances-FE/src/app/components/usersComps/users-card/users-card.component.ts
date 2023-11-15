import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { User } from 'src/entities/User';

@Component({
  selector: 'app-users-card',
  templateUrl: './users-card.component.html',
  styleUrls: ['./users-card.component.css'],
})
export class UsersCardComponent implements OnInit {
  @Input()
  _user!: User;
  _userType!: string;
  @Output() getEditableUser = new EventEmitter<User>();
  _editableUser!: User;
  _userActive!: string;

  ngOnInit(): void {
    this.setUserTipe();
    this.setUserActive();
  }

  sendEditableUser() {
    this._editableUser = this._user;
    this.getEditableUser.emit(this._editableUser);
  }

  setUserTipe() {
    if (this._user.usertype == 0) {
      this._userType = 'Admin';
    } else if (this._user.usertype == 1) {
      this._userType = 'Empleador';
    } else if (this._user.usertype == 2) {
      this._userType = 'Solicitante';
    }
  }

  setUserActive() {
    if (this._user.isActive == 1) {
      this._userActive = 'Activo';
    } else if (this._user.isActive == 1) {
      this._userActive = 'Inactivo';
    }
  }
}
