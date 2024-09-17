import { Component } from '@angular/core';
import { UserService } from '../../user/user.service';
import { Observable, map } from 'rxjs';
import { User } from '../../user/user';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-followers',
  standalone: true,
  imports: [AsyncPipe],
  templateUrl: './followers.component.html',
  styleUrl: './followers.component.css'
})
export class FollowersComponent {
  followers$: Observable<User[]>

  constructor(private userService: UserService) {
    this.followers$ = this.userService.getFollowers().pipe(map(userFollowers => {
      console.log("followers", userFollowers);
      return userFollowers.list;
    }))
  }
}
