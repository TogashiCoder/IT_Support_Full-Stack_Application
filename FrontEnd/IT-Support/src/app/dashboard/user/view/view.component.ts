import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user.model';
import { ActivatedRoute, Router } from '@angular/router';




@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent implements OnInit {

  user!:User;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService:UserService
  ){}


  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.loadEquipment(id);
    }
  }



  loadEquipment(id:string){
    this.userService.getUserById(parseInt(id)).subscribe(
      data =>{
        this.user =data;
      },
      error => {
        console.error('Error fetching user:', error);
      }
    )
  }


  editUser(): void {
    this.router.navigate(['../../edit', this.user.id], { relativeTo: this.route });
  }


  deleteUser(): void {
    if (confirm('Are you sure you want to delete this equipment?')) {
      this.userService.deleteUser(this.user.id).subscribe(
        () => {
          this.router.navigate(['../../'], { relativeTo: this.route });
        },
        error => {
          console.error('Error deleting equipment:', error);
        }
      );
    }
  }

  goBack(): void {
    this.router.navigate(['../../'], { relativeTo: this.route });
  }






}
