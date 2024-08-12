import { Component ,OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class AddComponent  implements OnInit{

  userForm: FormGroup;


  constructor(private fb: FormBuilder,
    private userService:UserService,
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
      role: ['USER', [Validators.required]]
    });
  }


  saveUser() {
    if (this.userForm.valid) {
      const userData = this.userForm.value;
      // Handle user data saving here
      console.log('User Data:', userData);
      this.userService.createNewUser(userData).subscribe(
        (createdUser: User) => {
          console.log('Equipment created:', createdUser);
          this.router.navigate(['../'], { relativeTo: this.activerouter });
        },
        (error) => {
          console.error('Error creating user:', error);
        }
      );
    }
  }
}
