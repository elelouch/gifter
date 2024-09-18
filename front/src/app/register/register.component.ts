import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../auth/auth.service';
import { ActivatedRoute, Router, RouterLink, RouterLinkActive } from '@angular/router';
import { BehaviorSubject, catchError, map, of } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { EditUserComponent } from '../edit-user/edit-user.component';
import { UserRegister } from '../user/user.register';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, AsyncPipe, EditUserComponent],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  creationMessage$: BehaviorSubject<string>

  constructor(private auth: AuthService, private route: ActivatedRoute, private router: Router) {
    this.creationMessage$ = new BehaviorSubject('');
  }

  onSubmit(userToRegister: UserRegister) {
    this.auth.register(userToRegister).pipe(map((newUser) => {
      setTimeout(() => {
        this.router.navigate(['..','login'], {relativeTo: this.route})
      }, 5 * 1000)
      return `Email: ${newUser.email} registered succesfully. You're about to be redirected to login in 5s.`
    }), catchError((error, caught) => {
      console.log(error, caught);
      return of('Error on registration, check if constraints are met');
    })).subscribe((message) => {
      this.creationMessage$.next(message);
    })
  }
}
