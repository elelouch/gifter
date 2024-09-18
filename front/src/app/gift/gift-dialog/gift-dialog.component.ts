import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

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
export class GiftDialogComponent {
  dialogRef = inject(MatDialogRef<GiftDialogComponent>);
  data = inject<object>(MAT_DIALOG_DATA); // value + validators array
  giftFormGroup: FormGroup

  constructor(private fb: FormBuilder) {
    this.giftFormGroup = this.fb.group(this.data);
  }

  onNoClick() {
    this.dialogRef.close();
  }
}
