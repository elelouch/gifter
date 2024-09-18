import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Gift } from '../gift';

@Component({
  selector: 'app-gift-dialog',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    MatDialogClose,
  ],
  templateUrl: './gift-dialog.component.html',
  styleUrl: './gift-dialog.component.css'
})
export class GiftDialogComponent implements OnInit{
  dialogRef = inject(MatDialogRef<GiftDialogComponent>);
  data = inject<Gift>(MAT_DIALOG_DATA); // value + validators array (USER DTO)
  giftFormGroup! : FormGroup

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    const gift = this.data
    this.giftFormGroup = this.fb.group({
      id: [gift.id ? gift.id : '0'],
      name: [gift.name ?? '',[Validators.required, Validators.maxLength(255), Validators.minLength(4)]],
      location: [gift.location ?? ''],
      imageUrl: [gift.imageUrl ?? '']
    })
  }

  onNoClick() {
    this.dialogRef.close();
  }
}
