import { Component, Input } from '@angular/core';
import { Category } from 'src/entities/Category';
import { User } from 'src/entities/User';
import { SeekersService } from 'src/app/services/seekers.service';

@Component({
  selector: 'app-select-category-card',
  templateUrl: './select-category-card.component.html',
  styleUrls: ['./select-category-card.component.css']
})
export class SelectCategoryCardComponent {

@Input()
_cat!: Category;
@Input()
_seeker!: User;

constructor(private seekersService: SeekersService) {
  console.log(this._seeker);
  console.log(this._cat);
}

addCategory() {
  this.seekersService.insertNewCategory(this._seeker.idCode!, this._cat).subscribe({
    next: (category: Category) => {
      console.log('Se agrego la categoria ', category);
      window.location.reload();
    },
    error: () => {
      console.log('error al agregar la categoria');
    },
  });
}

}
