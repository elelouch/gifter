import { Component, Input } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { Gift } from './gift';
import { BehaviorSubject, EMPTY, Observable, catchError, map, switchMap, tap } from 'rxjs';
import { GiftService } from './gift.service';
import { AsyncPipe } from '@angular/common';
import { User } from '../user/user';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user/user.service';
import { FriendService } from '../navbar/friend.service';

@Component({
  selector: 'app-gift',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, AsyncPipe],
  templateUrl: './gift.component.html',
  styleUrl: './gift.component.css',
})
export class GiftComponent {
  gifts$: BehaviorSubject<Gift[]>;
  isFriend$: Observable<boolean>;
  displayForm: boolean;
  giftFormGroup: FormGroup;
  isLogged = false;
  username = ""

  constructor(private fb: FormBuilder, private giftService: GiftService, route: ActivatedRoute, private userService: UserService, private friendService: FriendService) {
    this.giftFormGroup = this.fb.group({
      id: ['0'],
      imageUrl: [''],
      location: [''],
      name: [''],
    });
    this.displayForm = false;
    this.isFriend$ = new BehaviorSubject<boolean>(false);
    this.gifts$ = new BehaviorSubject<Gift[]>([]);
    this.isFriend$ = route.params.pipe(switchMap(params => {
      this.username = params['id'];
      this.isLogged = this.userService.isLoggedUser(this.username);
      this.giftService.getCurrentGifts(this.username).subscribe(dto => this.gifts$.next(dto.list))
      return this.friendService.getFriendRequest(params['id']).pipe(map(dto => dto.used));
    }), catchError(() => EMPTY))


  }

  onSubmit() {
    const gift: Gift = this.giftFormGroup.getRawValue();
    this.displayForm = false;
    this.addGift(gift);
    this.giftFormGroup.reset();
    this.giftFormGroup.patchValue({id: '0'});
  }

  addGift(gift: Gift) {
    this.giftService.updateGift(gift).subscribe((giftUpdated) => {
      const found = this.findById(giftUpdated.id);
      if (found) {
        found.imageUrl = giftUpdated.imageUrl;
        found.location = giftUpdated.location;
        found.name = giftUpdated.name;
      } else {
        const currList = this.gifts$.getValue();
        currList.push(giftUpdated);
        this.gifts$.next(currList);
      }
    });
  }

  findById(id: number){
    return this.gifts$.getValue().find((curr) => curr.id === id);
  }

  removeGiftById(id: number) {
    this.giftService.deleteGift(id).subscribe(() => {
      const list = this.gifts$.getValue();
      const i = list.findIndex((curr) => curr.id === id)
      list.splice(i,  1);
      this.gifts$.next(list);
    });
  }

  updateGift(id: number) {
    const found = this.findById(id)!;
    this.giftFormGroup.setValue(found);
    this.openForm();
  }

  openForm() {
    this.displayForm = true;
  }

  closeForm() {
    this.displayForm = false;
  }
}
