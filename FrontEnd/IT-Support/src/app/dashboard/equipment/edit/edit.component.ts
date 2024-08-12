import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Equipment } from 'src/app/models/equipment';
import { EquipmentService } from 'src/app/services/equipment.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class EditComponent implements OnInit {
  equipmentForm!: FormGroup;
  maxDate: string;
  selectedImagePath: string | null = null;
  imageWasSelected: boolean = false;
  equipmentId!: number;
  formChanged: boolean = false;

  constructor(
    private fb: FormBuilder,
    private equipmentService: EquipmentService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.maxDate = new Date().toISOString().split('T')[0];
  }

  ngOnInit() {
    this.equipmentId = this.activatedRoute.snapshot.params['id'];
    this.equipmentForm = this.fb.group({
      equipmentName: ['', [Validators.minLength(3)]],
      purchaseDate: ['', [this.dateValidator]],
      assetValue: ['', [Validators.min(0)]],
      serialNumber: ['']
    });

    this.equipmentService.getEquipmentById(this.equipmentId).subscribe(
      (equipment: Equipment) => {
        this.equipmentForm.patchValue(equipment);
        this.selectedImagePath = equipment.imageUrl;
      },
      (error) => {
        console.error('Error fetching equipment:', error);
      }
    );

    // Track form changes
    this.equipmentForm.valueChanges.subscribe(() => {
      this.formChanged = true;
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
      this.selectedImagePath = file.name;
      this.imageWasSelected = true;
      this.formChanged = true; // Mark form as changed
    }
  }

  updateEquipment() {
    if (this.equipmentForm.valid && this.formChanged) {
      const updatedEquipmentData = {
        ...this.equipmentForm.value,
        imageUrl: this.selectedImagePath || this.equipmentForm.value.imageUrl
      };

      this.equipmentService.updateEquipment(this.equipmentId, updatedEquipmentData).subscribe(
        (updatedEquipment: Equipment) => {
          console.log('Equipment updated:', updatedEquipment);
          this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
        },
        (error) => {
          console.error('Error updating equipment:', error);
        }
      );
    }
  }
}
