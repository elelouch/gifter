import { Component } from '@angular/core';
import { UserService } from './user.service';
import { User } from './user';
import { BehaviorSubject, Observable, map, switchMap } from 'rxjs';
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
  user$: Observable<User>;
  isOwner = false
  requestAlreadySent$: BehaviorSubject<FriendRequest | null>

  constructor(userService: UserService, route: ActivatedRoute, private friendService: FriendService) {
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

    this.requestAlreadySent$ = new BehaviorSubject<FriendRequest | null>(null);
  }

  sendFriendRequest(userId: number) {
    this.friendService.friendRequest(Number(userId)).subscribe((friendReq) => {
      this.requestAlreadySent$.next(friendReq)
    })
  }

  deleteFriendRequest(requestId: number) {
    this.friendService.removeFriendRequest(Number(requestId)).subscribe(()=>{
      this.requestAlreadySent$.next(null)
    })
  }
}
