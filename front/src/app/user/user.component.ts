import { Component } from '@angular/core';
import { UserService } from './user.service';
import { User } from './user';
import { BehaviorSubject, Observable, tap, switchMap, catchError, EMPTY, map } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { GiftComponent } from '../gift/gift.component';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { FriendService } from '../navbar/friend.service';
import { FriendRequest } from '../friend/friend.request';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [AsyncPipe, FormsModule, GiftComponent, RouterOutlet ],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css',
})
export class UserComponent {
  user$ = new BehaviorSubject<User>({} as User);
  logged = false;
  isFriend = false;
  followReq$: BehaviorSubject<FriendRequest | null>;

  constructor(userService: UserService, route: ActivatedRoute, private friendService: FriendService) {
    this.followReq$ = new BehaviorSubject<FriendRequest | null>(null);

    route.params.pipe(switchMap(params => {
      const username = params['id']
      this.logged = userService.isLoggedUser(username);
      return userService.getByUsername(username)
    }),tap(curr => this.user$.next(curr)),
    switchMap((curr) =>{
      return this.friendService.getFriendRequest(curr.username)
    }),tap(fr => {
      this.followReq$.next(fr);
    }),catchError(()=>{
      this.followReq$.next(null);
      return EMPTY
    })).subscribe()
  }

  sendFriendRequest(userId: number) {
    this.friendService.sendFriendRequest(Number(userId)).subscribe((friendReq) => {
      this.followReq$.next(friendReq)
    })
  }

  deleteFriendRequest(requestId: number) {
    this.friendService.removeFriendRequest(Number(requestId)).subscribe(()=>{
      this.followReq$.next(null)
    })
  }
}
