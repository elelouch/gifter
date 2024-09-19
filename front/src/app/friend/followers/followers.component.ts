import { Component, OnInit } from '@angular/core';
import { UserService } from '../../user/user.service';
import { Observable, map } from 'rxjs';
import { User } from '../../user/user';
import { AsyncPipe } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { MatListModule } from '@angular/material/list';

@Component({
  selector: 'app-followers',
  standalone: true,
  imports: [AsyncPipe, RouterLink, RouterLinkActive, MatListModule],
  templateUrl: './followers.component.html',
  styleUrl: './followers.component.css'
})
export class FollowersComponent implements OnInit{
  followers$!: Observable<User[]>

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.followers$ = this.userService.getFollowers().pipe(map(userFollowers => userFollowers.list))
  }
}
