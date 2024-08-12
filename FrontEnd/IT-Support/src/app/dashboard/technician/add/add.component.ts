import { Component ,OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Technician } from 'src/app/models/technician.model';
import { TechnicianService } from 'src/app/services/technician.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class AddComponent implements OnInit {

  userForm: FormGroup;


  constructor(private fb: FormBuilder,
    private technicianService:TechnicianService,
    private router: Router,
    private activerouter: ActivatedRoute

  ) {
    this.userForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      role: ['', [Validators.required]]
    });
  }

  ngOnInit() {
    this.userForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      role: ['TECHNICIAN', [Validators.required]]
    });
  }


  saveUser() {
    if (this.userForm.valid) {
      const userData = this.userForm.value;
      // Handle user data saving here
      console.log('User Data:', userData);
      this.technicianService.createNewTechnician(userData).subscribe(
        (createdTechnician: Technician) => {
          console.log('Technician created:', createdTechnician);
          this.router.navigate(['../'], { relativeTo: this.activerouter });
        },
        (error) => {
          console.error('Error creating Technician:', error);
        }
      );
    }
  }


}
