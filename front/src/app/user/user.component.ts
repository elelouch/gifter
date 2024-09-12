import { Component } from '@angular/core';
import { UserService } from './user.service';
import { User } from './user';
import { Observable } from 'rxjs';
import { GiftService } from '../gift/gift.service';
import { Gift } from '../gift/gift';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [AsyncPipe],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {
  user$?: Observable<User>
  gifts$?: Observable<Gift[]>

  constructor(private userService: UserService, private giftService: GiftService) {
    this.user$ = this.userService.getCurrentUser()
    this.gifts$ = this.giftService.getCurrentGifts()
  }
}
