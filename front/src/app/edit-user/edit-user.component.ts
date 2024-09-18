import { AsyncPipe } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserRegister } from '../user/user.register';

@Component({
  selector: 'app-edit-user',
  standalone: true,
  imports: [ReactiveFormsModule , AsyncPipe],
  templateUrl: './edit-user.component.html',
  styleUrl: './edit-user.component.css'
})
export class EditUserComponent {
  userForm: FormGroup
  @Output() newUser = new EventEmitter<UserRegister>();

  constructor(fb: FormBuilder) {
    this.userForm = fb.group({
      firstName: [""],
      lastName: [""],
      username: ["", [Validators.required, Validators.pattern("^[a-zA-Z0-9]+([_-]?[a-zA-Z0-9])*$")]],
      password: ["", [Validators.required, Validators.pattern("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")]],
      email: ["", [Validators.required, Validators.email]],
    })

  }

  onSubmit() {
    this.newUser.emit(this.userForm.getRawValue());
  }
}
