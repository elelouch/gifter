import { Component, EventEmitter, Input, Output } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { Gift } from './gift';
import { BehaviorSubject, Observable } from 'rxjs';
import { GiftService } from './gift.service';
import { UpdateGift } from './update.gift';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-gift',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, AsyncPipe],
  templateUrl: './gift.component.html',
  styleUrl: './gift.component.css',
})
export class GiftComponent {
  giftsSubject: BehaviorSubject<Gift[]>;
  displayForm: boolean;
  giftFormGroup: FormGroup;

  constructor(private fb: FormBuilder, private giftService: GiftService) {
    this.giftFormGroup = this.fb.group({
      id: ['0'],
      imageUrl: [''],
      location: [''],
      name: [''],
    });
    this.displayForm = false;
    this.giftService.getCurrentGifts().subscribe((updt) => {
      this.giftsSubject.next(updt.list);
    });
    this.giftsSubject = new BehaviorSubject<Gift[]>([]);
  }

  onSubmit() {
    const gift: Gift = this.giftFormGroup.getRawValue();
    this.displayForm = false;
    this.addGift();
    this.giftFormGroup.reset();
  }

  applyChanges() {
    this.giftService
      .updateGifts(this.giftsSubject.getValue())
      .subscribe((updt) => {
        this.giftsSubject.next(updt.list);
      });
  }

  private addGift() {
    const giftList = this.giftsSubject.value;
    giftList.push(gift);
    this.giftsSubject.next(giftList);
  }

  findGift(gift: Gift) {
    const giftList = this.giftsSubject.value;
    return gift.id != 0
      ? giftList.find((curr) => curr.id == gift.id)
      : giftList.find(
          (curr) =>
            curr.imageUrl === gift.imageUrl &&
            curr.name === gift.name &&
            curr.location === gift.location
        );
  }

  editGift(gift: Gift) {
    const giftFound = this.findGift(gift);
    if (giftFound) {
      giftFound.imageUrl = gift.imageUrl;
      giftFound.location = gift.location;
      giftFound.name = gift.name;
    }
  }

  openForm() {
    this.displayForm = true;
  }

  closeForm() {
    this.displayForm = false;
  }
}
