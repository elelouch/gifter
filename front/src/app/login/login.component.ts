import { Component } from '@angular/core';
import { FormBuilder,  FormGroup, FormsModule,  ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../auth/auth.service';
import { AdminService } from '../admin/admin.service';
import { EMPTY, Observable, catchError, isEmpty, map } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { UserAuth } from '../auth/user.auth';
import { ActivatedRoute, Router, RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, RouterLinkActive, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm: FormGroup
  login$?: Observable<UserAuth>

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private admin: AdminService,
    private router: Router,
    private route: ActivatedRoute
  ){
    this.loginForm = this.fb.group({
      email: ["", [Validators.maxLength(255), Validators.minLength(3),Validators.email]],
      password: ["", [Validators.maxLength(255), Validators.minLength(3)]],
    })
  }

  onSubmit() {
    this.authService.login(this.loginForm.getRawValue()).subscribe((userAuthObs) => {
      userAuthObs.pipe(
        map((userAuth) => {
          if(userAuth.token) {
            this.router.navigate(["user"], {relativeTo: this.route})
          }
        }),
        catchError((error, catches)=>{
          console.log(error);
          return EMPTY;
      }))
    });
  }
}
