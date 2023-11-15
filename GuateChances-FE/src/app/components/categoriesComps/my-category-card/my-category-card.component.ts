import { Component, Input } from '@angular/core';
import { Category } from 'src/entities/Category';
import { User } from 'src/entities/User';
import { SeekersService } from 'src/app/services/seekers.service';

@Component({
  selector: 'app-my-category-card',
  templateUrl: './my-category-card.component.html',
  styleUrls: ['./my-category-card.component.css'],
})
export class MyCategoryCardComponent {
  @Input()
  _cat!: Category;
  @Input()
  _seeker!: User;

  constructor(private seekersService: SeekersService) {
  }

  delCategory() {
    this.seekersService.deleteCategory(this._seeker.idCode!, this._cat).subscribe({
      next: (category: Category) => {
        console.log('Se Elimino la categoria ', category);
        window.location.reload();
      },
      error: () => {
        console.log('error al eliminar la categoria');
      },
    });
  }
}
