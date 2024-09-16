import { Component } from '@angular/core';
import { UserService } from './user.service';
import { User } from './user';
import { Observable, map, switchMap } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { GiftComponent } from '../gift/gift.component';
import { ActivatedRoute, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [AsyncPipe, FormsModule, GiftComponent, RouterOutlet ],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css',
})
export class UserComponent {
  user$: Observable<User>;
  isOwner = false

  constructor(userService: UserService, route: ActivatedRoute) {
    this.user$ = route.params.pipe(switchMap(params => {
      const selectedUsername = params['id'];
      return userService.getByUsername(selectedUsername);
    }), map(curr => {
      userService.getCurrentUser().subscribe((logged) => {
        this.isOwner = logged.email === curr.email &&
          logged.id === curr.id &&
          logged.username === curr.username;
      })
      return curr;
    }))
  }
}
