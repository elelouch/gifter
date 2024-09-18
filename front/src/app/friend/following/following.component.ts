import { Component } from '@angular/core';
import { Observable, map } from 'rxjs';
import { User } from '../../user/user';
import { UserService } from '../../user/user.service';
import { AsyncPipe } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-following',
  standalone: true,
  imports: [AsyncPipe, RouterLink, RouterLinkActive],
  templateUrl: './following.component.html',
  styleUrl: './following.component.css'
})
export class FollowingComponent {
  following$: Observable<User[]>

  constructor(private userService: UserService) {
    this.following$ = this.userService.getFollowing().pipe(map(userFollowers => userFollowers.list))
  }
}
