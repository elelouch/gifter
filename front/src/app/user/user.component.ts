import { Component } from '@angular/core';
import { UserService } from './user.service';
import { User } from './user';
import { BehaviorSubject, Observable, tap, switchMap, catchError, EMPTY, map } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule } from '@angular/forms';
import { GiftComponent } from '../gift/gift.component';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { FriendService } from '../navbar/friend.service';
import { FriendRequest } from '../friend/friend.request';
import { EditUserComponent } from '../edit-user/edit-user.component';
import { UpdateUser } from '../edit-user/edit-user';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [AsyncPipe, FormsModule, GiftComponent, RouterOutlet, EditUserComponent ],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css',
})
export class UserComponent {
  user$ = new BehaviorSubject<User>({} as User);
  followReq$: BehaviorSubject<FriendRequest | null>;
  logged = false;
  isFriend = false;

  constructor(private userService: UserService, route: ActivatedRoute, private friendService: FriendService) {
    this.followReq$ = new BehaviorSubject<FriendRequest | null>(null);

    route.params.pipe(switchMap(params => {
      const username = params['id']
      this.logged = this.userService.isLoggedUser(username);
      return this.userService.getByUsername(username)
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

  updateUser(userToUpdate: UpdateUser) {
    this.userService.updateUser(userToUpdate).subscribe(updatedUser => {
      this.user$.next(updatedUser);
    })
  }

}
