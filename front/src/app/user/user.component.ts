import { Component, OnInit, inject } from '@angular/core';
import { UserService } from './user.service';
import { User } from './user';
import { BehaviorSubject, tap, switchMap, catchError, EMPTY } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { GiftComponent } from '../gift/gift.component';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { FriendService } from '../navbar/friend.service';
import { FriendRequest } from '../friend/friend.request';
import { EditUserComponent } from '../edit-user/edit-user.component';
import { UpdateUser } from '../edit-user/edit-user';
import { MatDialog } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [AsyncPipe, FormsModule, GiftComponent, RouterOutlet, EditUserComponent, MatIconModule, MatButtonModule],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css',
})
export class UserComponent implements OnInit{
  user$ = new BehaviorSubject<User>({} as User);
  followReq$ = new BehaviorSubject<FriendRequest | null>(null);
  logged = false;
  isFollower = false;
  showForm = false;
  userDialog = inject(MatDialog);

  constructor(private userService: UserService,private route: ActivatedRoute, private friendService: FriendService) {}

  ngOnInit() {
    this.route.params.pipe(switchMap(params => {
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

  openDialog() {
      const user = this.user$.getValue();
      const refDialog = this.userDialog.open(EditUserComponent, {data:user})
      refDialog.afterClosed().subscribe(userUpdate => {
        if(!userUpdate) return;
        this.userService.updateUser(userUpdate).subscribe();
      })
  }

  sendFriendRequest(userId: number) {
    this.friendService.sendFriendRequest(Number(userId)).subscribe((followReq) => {
      this.isFollower = followReq.used;
      this.followReq$.next(followReq)
    })
  }

  deleteFriendRequest(requestId: number) {
    this.friendService.removeFriendRequest(Number(requestId)).subscribe(()=>{
      this.isFollower = false
      this.followReq$.next(null)
    })
  }

  updateUser(userToUpdate: UpdateUser) {
    this.userService.updateUser(userToUpdate).subscribe(updatedUser => {
      this.showForm = false;
      this.user$.next(updatedUser);
    })
  }

}
