import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AuthService } from '../auth/auth.service';
import {
    BehaviorSubject,
    catchError,
    map,
    of,
} from 'rxjs';
import {
  Router,
  RouterLink,
  RouterLinkActive,
} from '@angular/router';
import { AsyncPipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, RouterLinkActive, RouterLink, AsyncPipe],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  loginForm: FormGroup;
  loginMessage$: BehaviorSubject<string>

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
  ) {
    this.loginForm = this.fb.group({
      email: [
        '',
        [Validators.maxLength(255), Validators.minLength(3), Validators.email],
      ],
      password: ['', [Validators.maxLength(255), Validators.minLength(3)]],
    });
    this.loginMessage$ = new BehaviorSubject('');
  }

  onSubmit() {
    this.authService.login(this.loginForm.getRawValue()).pipe(map((user) => {
      this.router.navigate(['app', 'user', user.username]);
      return "";
    }), catchError((error: HttpErrorResponse,catchs) => {
      console.log(error);
      const errBody = error.error
      return of(`${errBody.reason ?? errBody.detail} Check if credentials are right.`);
    })).subscribe(msg => {
      this.loginMessage$.next(msg);
    })
  }
}
