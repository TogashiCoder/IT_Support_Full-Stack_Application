import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Equipment } from 'src/app/models/equipment';
import { EquipmentService } from 'src/app/services/equipment.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class AddComponent implements OnInit {
  equipmentForm!: FormGroup;
  maxDate: string;
  selectedImagePath: string | null = null;
  imageWasSelected:boolean =false;


  constructor(
    private fb: FormBuilder,
    private equipmentService: EquipmentService,
    private router: Router,
    private activerouter: ActivatedRoute
  ) {
    this.maxDate = new Date().toISOString().split('T')[0];
  }

  ngOnInit() {
    this.equipmentForm = this.fb.group({
      // imageUrl: ['',[Validators.required]],
      equipmentName: ['', [Validators.required, Validators.minLength(3)]],
      purchaseDate: ['', [Validators.required, this.dateValidator]],
      assetValue: ['', [Validators.required, Validators.min(0)]],
      serialNumber: ['', Validators.required]
    });
  }

  dateValidator(control: AbstractControl): {[key: string]: any} | null {
    const currentDate = new Date();
    const inputDate = new Date(control.value);

    if (inputDate > currentDate) {
      return { 'futureDate': true };
    }
    return null;
  }

  onFileSelected(event: Event) {
    const file = (event.target as HTMLInputElement).files?.[0];
    if (file) {
      this.selectedImagePath = file.name; // Extract the file name
      this.imageWasSelected =true;
    }
  }

  saveEquipment() {
    if (this.equipmentForm.valid && this.selectedImagePath) {
      const equipmentData = {
        ...this.equipmentForm.value,
        imageUrl: this.selectedImagePath // Use the file name as the image URL
      };

      this.equipmentService.createNewEquipment(equipmentData).subscribe(
        (createdEquipment: Equipment) => {
          console.log('Equipment created:', createdEquipment);
          this.router.navigate(['../'], { relativeTo: this.activerouter });
        },
        (error) => {
          console.error('Error creating equipment:', error);
        }
      );
    }
  }
}
