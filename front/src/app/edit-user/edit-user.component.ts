import { AsyncPipe } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserRegister } from '../user/user.register';
import { User } from '../user/user';
import {MatButtonModule} from '@angular/material/button';
import {MatFormField} from '@angular/material/form-field';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import { MAT_DIALOG_DATA, MatDialogClose, MatDialogRef } from '@angular/material/dialog';
import { UpdateUser } from './edit-user';

@Component({
  selector: 'app-edit-user',
  standalone: true,
  imports: [ReactiveFormsModule , AsyncPipe, MatButtonModule, MatCardModule, MatInputModule, MatFormField, MatDialogClose],
  templateUrl: './edit-user.component.html',
  styleUrl: './edit-user.component.css'
})

export class EditUserComponent implements OnInit {
  dialogRef = inject(MatDialogRef<EditUserComponent>);
  data = inject<UpdateUser>(MAT_DIALOG_DATA); // value + validators array (USER DTO)
  userFormGroup!: FormGroup

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    const userUpdate = this.data;
    this.userFormGroup = this.fb.group({
      username:[userUpdate.username ?? ''],
      email: [],
      firstName: [],
      lastName: [],
      password: []
    });
  }

  onNoClick() {
    this.dialogRef.close();
  }

}
