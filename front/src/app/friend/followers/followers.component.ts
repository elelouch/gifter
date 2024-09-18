import { Component } from '@angular/core';
import { UserService } from '../../user/user.service';
import { Observable, map } from 'rxjs';
import { User } from '../../user/user';
import { AsyncPipe } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-followers',
  standalone: true,
  imports: [AsyncPipe, RouterLink, RouterLinkActive],
  templateUrl: './followers.component.html',
  styleUrl: './followers.component.css'
})
export class FollowersComponent {
  followers$: Observable<User[]>

  constructor(private userService: UserService) {
    this.followers$ = this.userService.getFollowers().pipe(map(userFollowers => userFollowers.list))
  }
}
