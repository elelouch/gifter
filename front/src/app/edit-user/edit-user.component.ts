import { AsyncPipe } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatFormField} from '@angular/material/form-field';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import { MAT_DIALOG_DATA, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { UpdateUser } from './edit-user';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-edit-user',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    AsyncPipe,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatFormField,
    MatDialogClose,
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
  ],
  templateUrl: './edit-user.component.html',
  styleUrl: './edit-user.component.css'
})

export class EditUserComponent implements OnInit {
  dialogRef = inject(MatDialogRef<EditUserComponent>);
  data = inject<UpdateUser>(MAT_DIALOG_DATA); // value + validators array (USER DTO)
  hide = true;
  userFormGroup!: FormGroup

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    const userUpdate = this.data;
    this.userFormGroup = this.fb.group({
      firstName: [userUpdate.firstName ?? ''],
      lastName: [userUpdate.lastName ?? ''],
      username: [userUpdate.username ?? '', [Validators.required, Validators.pattern("^[a-zA-Z0-9]+([_-]?[a-zA-Z0-9])*$")]],
      password: [userUpdate.password ?? '', [Validators.required, Validators.pattern("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")]],
      email: [userUpdate.email ?? '', [Validators.required, Validators.email]],
    })
  }

  onNoClick() {
    this.dialogRef.close();
  }

}
