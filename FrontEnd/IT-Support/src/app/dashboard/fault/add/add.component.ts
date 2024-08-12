import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Fault } from 'src/app/models/fault.model'; // Adjust this path if necessary
import { FaultService } from 'src/app/services/fault.service'; // Adjust this path if necessary

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class AddComponent {

  faultForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private faultService: FaultService,
    private router: Router,
    private activerouter: ActivatedRoute
  ) {
    this.faultForm = this.fb.group({
      description: ['', [Validators.required, Validators.minLength(5)]]
    });
  }

  ngOnInit() {
  }

  saveFault() {
    if (this.faultForm.valid) {
      const faultData = this.faultForm.value;
      console.log('Fault Data:', faultData);
      this.faultService.createNewFault(faultData).subscribe(
        (createdFault: Fault) => {
          console.log('Fault created:', createdFault);
          this.router.navigate(['../'], { relativeTo: this.activerouter });
        },
        (error) => {
          console.error('Error creating fault:', error);
        }
      );
    }
  }

}
