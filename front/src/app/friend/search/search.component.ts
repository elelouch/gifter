import { Component } from '@angular/core';
import { UserService } from '../../user/user.service';
import { BehaviorSubject } from 'rxjs';
import { User } from '../../user/user';
import { AsyncPipe } from '@angular/common';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [AsyncPipe, ReactiveFormsModule, RouterLinkActive, RouterLink, RouterOutlet],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {
  usersFound$: BehaviorSubject<User[]>
  userInput: FormControl;

  constructor(private userService: UserService){
    this.usersFound$ = new BehaviorSubject<User[]>([]);
    this.userInput = new FormControl('');
    this.userInput.valueChanges.subscribe(val => {
      if(val.length > 3) this.getByLikeUsername(val);
    })
  }

  getByLikeUsername(username: string) {
    this.userService.getByLikeUsername(username).subscribe((users) => {
      this.usersFound$.next(users);
    })
  }

}
