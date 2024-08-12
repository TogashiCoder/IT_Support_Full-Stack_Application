import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TechnicianService } from 'src/app/services/technician.service';



@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class EditComponent implements OnInit {

  userForm!: FormGroup;
  userId!: number;

  constructor(
    private fb: FormBuilder,
    private technicianService: TechnicianService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.userForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      role: [{ value: '', disabled: true }] // Initialize as disabled
    });

    this.activatedRoute.params.subscribe(params => {
      this.userId = +params['id']; // Retrieve user ID from route parameters
      this.technicianService.getTechnicianById(this.userId).subscribe(user => {
        this.userForm.patchValue(user);
      });
    });
  }


  updateUser() {
    if (this.userForm.valid) {
      const updatedUser = {
        ...this.userForm.value,
        role: this.userForm.get('role')?.value // Ensure role value is included
      };
      this.technicianService.updateTechnician(this.userId, updatedUser).subscribe(() => {
        this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
      });
    }
  }




}