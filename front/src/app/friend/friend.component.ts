import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { FriendService } from '../navbar/friend.service';
import { AsyncPipe } from '@angular/common';
import { FriendRequest } from './friend.request';
import { BehaviorSubject, map } from 'rxjs';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { SearchComponent } from './search/search.component';
import { FollowersComponent } from './followers/followers.component';
import {MatTabsModule} from '@angular/material/tabs';
import { FollowingComponent } from './following/following.component';
import { MatDivider } from '@angular/material/divider';

@Component({
  selector: 'app-friend',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    MatListModule,
    MatButtonModule,
    MatIconModule,
    MatDivider,
    SearchComponent,
    FollowersComponent,
    FollowingComponent,
    FriendComponent,
    MatTabsModule
  ],
  templateUrl: './friend.component.html',
  styleUrl: './friend.component.css'
})
export class FriendComponent {
  followRequests$: BehaviorSubject<FriendRequest[]>

  constructor(private friendService: FriendService) {
    this.followRequests$ = new BehaviorSubject<FriendRequest[]>([]);
    this.friendService.getPendingRequests().pipe(map(pend => pend.pendingList)).subscribe(list=>{
      this.followRequests$.next(list);
    });
  }

  useRequest(id: number) {
    this.friendService.useFriendRequest(id).subscribe(() => {
      this.removeRequest(id);
    })
  }

  deleteRequest(id: number) {
    this.friendService.removeFriendRequest(id).subscribe(() => {
      this.removeRequest(id);
    });
  }

  removeRequest(id: number) {
    const pendingRequests = this.followRequests$.getValue()
    const i = pendingRequests.findIndex((curr) => curr.id === id);
    pendingRequests.splice(i, 1);
    this.followRequests$.next(pendingRequests);
  }
}
