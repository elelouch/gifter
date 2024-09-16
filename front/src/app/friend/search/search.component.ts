import { Component } from '@angular/core';
import { FriendService } from '../../navbar/friend.service';
import { BehaviorSubject } from 'rxjs';
import { User } from '../../user/user';
import { AsyncPipe } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [AsyncPipe, RouterLink, ReactiveFormsModule],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {
  usersFound$: BehaviorSubject<User[]>
  userInput: FormControl;

  constructor(private friendService: FriendService, private router: Router, private route: ActivatedRoute){
    this.usersFound$ = new BehaviorSubject<User[]>([]);
    this.userInput = new FormControl('');
    this.userInput.valueChanges.subscribe(val => {
      this.getByLikeUsername(val);
    })
  }

  getByLikeUsername(username: string) {
    this.friendService.getByLikeUsername(username).subscribe((users) => {
      this.usersFound$.next(users);
    })
  }

  redirectToProfile(username: string) {
    this.router.navigate(['..', '..', 'user', username], {relativeTo: this.route})
  }
}
