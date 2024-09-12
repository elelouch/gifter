import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../auth/auth.service';
import { Observable } from 'rxjs';
import { User } from '../user/user';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink, RouterLinkActive],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm: FormGroup

  constructor(private fb: FormBuilder, private auth: AuthService) {
    this.registerForm = fb.group({
      firstName: [""],
      lastName: [""],
      username: ["", Validators.required],
      password: ["", Validators.required],
      email: ["", Validators.required],
    })
  }

  onSubmit() {
    const dto = this.registerForm.getRawValue();
    this.auth.register(dto).subscribe((value) => {
      console.log(value);
      if(value) console.log("route to login component once registered");
    })
  }
}
