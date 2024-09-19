import { Component, inject } from '@angular/core';
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
    EMPTY,
    catchError,
    map,
    of,
    switchMap,
} from 'rxjs';
import {
  Router,
  RouterLink,
  RouterLinkActive,
} from '@angular/router';
import { AsyncPipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatFormField} from '@angular/material/form-field';
import { EditUserComponent } from '../edit-user/edit-user.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, RouterLinkActive, RouterLink, AsyncPipe, MatButtonModule, MatCardModule, MatInputModule, MatFormField],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  loginForm: FormGroup;
  loginMessage$: BehaviorSubject<string>
  userDialog = inject(MatDialog);

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

  openDialog() {
    const dialogRef = this.userDialog.open(EditUserComponent, {data:{}});

    dialogRef.afterClosed().pipe(switchMap(userUpdt => {
      return this.authService.register(userUpdt);
    }),catchError((error) => {
      console.log(error);
      const errBody = error.error
      this.loginMessage$.next(`${errBody.reason ?? errBody.detail}`);
      return EMPTY;
    })).subscribe((user) => {
      if(user) this.loginMessage$.next(`User ${user.username}  succesfully registered`);
    })
  }

  onSubmit() {
    this.authService.login(this.loginForm.getRawValue()).pipe(map((user) => {
      this.router.navigate(['app', 'user', user.username]);
      return "";
    }), catchError((error) =>{
      console.log(error);
      const errBody = error.error
      this.loginMessage$.next(`${errBody.reason ?? errBody.detail}`);
      return EMPTY;
    })).subscribe(msg => {
      this.loginMessage$.next(msg);
    })
  }

  errorHandler(error: HttpErrorResponse) {
  }
}
