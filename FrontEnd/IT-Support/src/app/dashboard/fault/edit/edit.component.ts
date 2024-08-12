import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Fault } from 'src/app/models/fault.model';
import { FaultService } from 'src/app/services/fault.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class EditComponent {
  faultForm!: FormGroup;
  faultId!: number;

  constructor(
    private fb: FormBuilder,
    private faultService: FaultService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.faultForm = this.fb.group({
      description: ['', [Validators.required, Validators.minLength(5)]]
    });

    this.activatedRoute.params.subscribe(params => {
      this.faultId = +params['id']; // Retrieve fault ID from route parameters
      this.faultService.getFaultById(this.faultId).subscribe(fault => {
        this.faultForm.patchValue(fault);
      });
    });
  }

  updateFault() {
    if (this.faultForm.valid) {
      const updatedFault = this.faultForm.value;
      this.faultService.updateFault(this.faultId, updatedFault).subscribe(() => {
        this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
      });
    }
  }

}
