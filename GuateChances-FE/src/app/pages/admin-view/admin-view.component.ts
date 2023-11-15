import { Component, OnInit } from '@angular/core';
import { User } from 'src/entities/User';
import { Offer } from 'src/entities/Offer';
import { UserService } from 'src/app/services/user.service';
import { OfferService } from 'src/app/services/offer.service';

@Component({
  selector: 'app-admin-view',
  templateUrl: './admin-view.component.html',
  styleUrls: ['./admin-view.component.css'],
})
export class AdminViewComponent implements OnInit {
  usersList: User[] = [];
  offersList: Offer[] = [];
  userEditable!: User;
  offerEditable!: Offer;

  constructor(private userService: UserService,
    private offerService: OfferService) {}

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe({
      next: (users: User[]) => {
        this.usersList = users;
      }
    });

    this.offerService.getAllOffers().subscribe({
      next: (offers: Offer[]) => {
        this.offersList = offers;
      }
    })
  }

  setEditableUser(User: User){
    this.userEditable = User;
    console.log(this.userEditable);
  }

  setEditableOffer(Offer: Offer){
    this.offerEditable = Offer;
    console.log(this.offerEditable);
  }

}
