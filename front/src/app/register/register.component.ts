import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../auth/auth.service';
import { ActivatedRoute, Router, RouterLink, RouterLinkActive } from '@angular/router';
import { BehaviorSubject, catchError, map, of } from 'rxjs';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink, RouterLinkActive, AsyncPipe],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm: FormGroup
  creationMessage$: BehaviorSubject<string>

  constructor(fb: FormBuilder, private auth: AuthService, private route: ActivatedRoute, private router: Router) {
    this.registerForm = fb.group({
      firstName: [""],
      lastName: [""],
      username: ["", Validators.required],
      password: ["", Validators.required],
      email: ["", Validators.required],
    })
    this.creationMessage$ = new BehaviorSubject('');
  }

  onSubmit() {
    const dto = this.registerForm.getRawValue();
    this.auth.register(dto).pipe(map((userRegistered) => {
      setTimeout(() => {
        this.router.navigate(['..','login'], {relativeTo: this.route})
      }, 5 * 1000)
      return `Email: ${userRegistered.email} registered succesfully. You're about to be redirected to login in 5s.`
    }), catchError((error, caught) => {
      console.log(error, caught);
      return of('Error on registration, check if constraints are met');
    })).subscribe((message) => {
      this.creationMessage$.next(message);
    })
  }
}
