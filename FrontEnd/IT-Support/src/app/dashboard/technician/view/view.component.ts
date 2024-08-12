import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Technician } from 'src/app/models/technician.model';
import { TechnicianService } from 'src/app/services/technician.service';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent implements OnInit {

  technician!:Technician;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private technicianService:TechnicianService
  ){}


  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.loadTechnician(id);
    }
  }


  loadTechnician(id:string){
    this.technicianService.getTechnicianById(parseInt(id)).subscribe(
      data =>{
        this.technician =data;
      },
      error => {
        console.error('Error fetching technician:', error);
      }
    )
  }

  editUser(): void {
    this.router.navigate(['../../edit', this.technician.id], { relativeTo: this.route });
  }


  deleteUser(): void {
    if (confirm('Are you sure you want to delete this equipment?')) {
      this.technicianService.deleteTechnician(this.technician.id).subscribe(
        () => {
          this.router.navigate(['../../'], { relativeTo: this.route });
        },
        error => {
          console.error('Error deleting technician:', error);
        }
      );
    }
  }

  goBack(): void {
    this.router.navigate(['../../'], { relativeTo: this.route });
  }


}
