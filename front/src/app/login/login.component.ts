import { Component } from '@angular/core';
import { FormBuilder,  FormGroup, FormsModule,  ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../auth/auth.service';
import { AdminService } from '../admin/admin.service';
import { User } from '../user/user';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm: FormGroup
  currentUsers: User[] =[]

  constructor(private fb: FormBuilder, private authService: AuthService, private admin: AdminService){
    this.loginForm = this.fb.group({
      email: ['', [Validators.email, Validators.maxLength(255), Validators.minLength(3)]],
      password: ['', [Validators.maxLength(255), Validators.minLength(3)]],
      builder: this.fb.group({
        gay: ['im gay']
      })
    })
  }

  onSubmit() {
    const email = this.loginForm.get("email")?.value;
    const password = this.loginForm.get("password")?.value;
    this.authService.login(email, password)
  }

  getAllUsers() {
    this.admin.getAllUsers().subscribe(userList => {
      this.currentUsers = userList
    })
  }
}
