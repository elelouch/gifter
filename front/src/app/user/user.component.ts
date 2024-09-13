import { Component } from '@angular/core';
import { UserService } from './user.service';
import { User } from './user';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { GiftService } from '../gift/gift.service';
import { Gift } from '../gift/gift';
import { AsyncPipe } from '@angular/common';
import { UpdateGift } from '../gift/update.gift';
import { FormBuilder, FormGroup, FormsModule } from '@angular/forms';
import { GiftComponent } from '../gift/gift.component';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [AsyncPipe, FormsModule, GiftComponent],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css',
})
export class UserComponent {
  user$?: Observable<User>;
  giftSubject: BehaviorSubject<Gift[]>;
  giftFormGroup: FormGroup;

  constructor(
    private userService: UserService,
    private giftService: GiftService,
    fb: FormBuilder
  ) {
    this.user$ = this.userService.getCurrentUser();
    this.giftFormGroup = fb.group({
      imageUrl: [''],
      location: [''],
      name: [''],
    });
    this.giftSubject = new BehaviorSubject<Gift[]>([]);
  }

  applyGiftsUpdate() {
    this.giftService
      .updateGifts(this.giftSubject.getValue())
      .subscribe((updt) => {
        this.giftSubject.next(updt.list);
      });
  }

  addGift(gift: Gift) {
    const giftList = this.giftSubject.value;
    const giftFound =
      gift.id !== 0 ? giftList.find((toFind) => toFind.id === gift.id) : {};
    const toAdd = { ...giftFound, ...gift };
    giftList.push(toAdd);
    this.giftSubject.next(giftList);
  }
}
