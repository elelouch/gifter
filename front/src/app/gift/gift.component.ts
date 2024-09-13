import { Component, EventEmitter, Input, Output } from '@angular/core';
import { GiftService } from './gift.service';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { Gift } from './gift';

@Component({
  selector: 'app-gift',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './gift.component.html',
  styleUrl: './gift.component.css',
})
export class GiftComponent {
  @Output() onNewGift = new EventEmitter();

  giftFormGroup: FormGroup;
  constructor(fb: FormBuilder) {
    this.giftFormGroup = fb.group({
      imageUrl: [''],
      location: [''],
      name: [''],
    });
  }

  onSubmit() {
    const gift: Gift = this.giftFormGroup.getRawValue();
    this.onNewGift.emit(gift);
    this.giftFormGroup.reset();
  }
}
