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
    this.addGift(gift);
    this.giftFormGroup.reset();
    this.giftFormGroup.setValue({ id: 0 });
  }

  addGift(gift: Gift) {
    this.giftService.updateGift(gift).subscribe((giftUpdated) => {
      console.log('gift added/receivied', giftUpdated);
      const found = this.findById(giftUpdated.id);
      if (found) {
        found.imageUrl = giftUpdated.imageUrl;
        found.location = giftUpdated.location;
        found.name = giftUpdated.name;
      } else {
        const currList = this.giftsSubject.getValue();
        currList.push(giftUpdated);
        this.giftsSubject.next(currList);
      }
    });
  }

  findById(id: number) {
    return this.giftsSubject.getValue().find((curr) => curr.id === id);
  }

  removeGift(id: number) {
    this.giftService.deleteGift(id).subscribe(() => {
      console.log('gift succesfully removed');
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
