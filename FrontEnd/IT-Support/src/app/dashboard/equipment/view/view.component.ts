import { Component, OnInit } from '@angular/core';
import { Equipment } from '../../../models/equipment';
import { ActivatedRoute, Router } from '@angular/router';
import { EquipmentService } from '../../../services/equipment.service';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent implements OnInit {
  equipment!: Equipment;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private equipmentService: EquipmentService
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.loadEquipment(id);
    }
  }

  loadEquipment(id: string): void {
    this.equipmentService.getEquipmentById(parseInt(id)).subscribe(
      data => {
        this.equipment = data;
      },
      error => {
        console.error('Error fetching equipment:', error);
      }
    );
  }

  editEquipment(): void {
    this.router.navigate(['../../edit', this.equipment.id], { relativeTo: this.route });
  }

  deleteEquipment(): void {
    if (confirm('Are you sure you want to delete this equipment?')) {
      this.equipmentService.deleteEquipment(this.equipment.id).subscribe(
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
