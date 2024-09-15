import { Component } from '@angular/core';
import { UserService } from './user.service';
import { User } from './user';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { Gift } from '../gift/gift';
import { AsyncPipe } from '@angular/common';
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
  constructor(private userService: UserService) {
    this.user$ = this.userService.getCurrentUser();
  }
}
