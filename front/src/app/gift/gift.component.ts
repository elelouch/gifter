import { Component, Input, OnInit, inject } from '@angular/core';
import {
  Validators,
} from '@angular/forms';
import { Gift } from './gift';
import { BehaviorSubject, EMPTY, catchError, map, switchMap } from 'rxjs';
import { GiftService } from './gift.service';
import { AsyncPipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user/user.service';
import { FriendService } from '../navbar/friend.service';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { GiftDialogComponent } from './gift-dialog/gift-dialog.component';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-gift',
  standalone: true,
  imports: [AsyncPipe, MatFormFieldModule, MatInputModule, MatButtonModule, MatListModule, MatIconModule],
  templateUrl: './gift.component.html',
  styleUrl: './gift.component.css',
})
export class GiftComponent implements OnInit{
  gifts$ = new BehaviorSubject<Gift[]>([]);
  isFriend$ = new BehaviorSubject<boolean>(false);
  isLogged = false;
  giftDialog = inject(MatDialog);


  constructor(private giftService: GiftService, private route: ActivatedRoute, private userService: UserService, private friendService: FriendService) {}

  ngOnInit(){
    this.route.params.pipe(switchMap(params => {
      const username = params['id'];
      this.isLogged = this.userService.isLoggedUser(username);
      this.giftService.getCurrentGifts(username).subscribe(dto => this.gifts$.next(dto.list))
      return this.friendService.getFriendRequest(params['id']);
    }),map(fr => this.isFriend$.next(fr.used)), catchError(() => EMPTY)).subscribe()
  }

  openDialog(giftId: number) {
    const found = this.findById(giftId);
    const dialogRef = this.giftDialog.open(GiftDialogComponent, {data:{...found}});

    dialogRef.afterClosed().subscribe(gift => {
      if(!gift) return;
      this.addGift(gift);
    })
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


}
