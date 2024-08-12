import { Component, OnInit } from '@angular/core';
import { FaultService } from 'src/app/services/fault.service';
import { Fault } from 'src/app/models/fault.model';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent {


  fault!: Fault;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private faultService: FaultService
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.loadFault(id);
    }
  }

  loadFault(id: string): void {
    this.faultService.getFaultById(parseInt(id)).subscribe(
      data => {
        this.fault = data;
      },
      error => {
        console.error('Error fetching fault:', error);
      }
    );
  }

  editFault(): void {
    this.router.navigate(['../../edit', this.fault.id], { relativeTo: this.route });
  }

  deleteFault(): void {
    if (confirm('Are you sure you want to delete this fault?')) {
      this.faultService.deleteFault(this.fault.id).subscribe(
        () => {
          this.router.navigate(['../../'], { relativeTo: this.route });
        },
        error => {
          console.error('Error deleting fault:', error);
        }
      );
    }
  }

  goBack(): void {
    this.router.navigate(['../../'], { relativeTo: this.route });
  }


}
