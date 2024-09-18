import { AsyncPipe } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserRegister } from '../user/user.register';
import { User } from '../user/user';

@Component({
  selector: 'app-edit-user',
  standalone: true,
  imports: [ReactiveFormsModule , AsyncPipe],
  templateUrl: './edit-user.component.html',
  styleUrl: './edit-user.component.css'
})
export class EditUserComponent implements OnInit {
  userForm = this.fb.group({}) as FormGroup
  @Output() newUser = new EventEmitter<UserRegister>();
  @Input() userToLoad?: User

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.userForm = this.fb.group({
      firstName: [this.userToLoad?.firstName ?? ""],
      lastName: [this.userToLoad?.lastName ?? ""],
      username: [this.userToLoad?.username ?? "", [Validators.required, Validators.pattern("^[a-zA-Z0-9]+([_-]?[a-zA-Z0-9])*$")]],
      password: [this.userToLoad?.password ?? "", [Validators.required, Validators.pattern("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")]],
      email: [this.userToLoad?.email ?? "", [Validators.required, Validators.email]],
    })
  }

  onSubmit() {
    this.newUser.emit(this.userForm.getRawValue());
  }
}
