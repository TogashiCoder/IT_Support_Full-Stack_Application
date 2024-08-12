import { Component,OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EquipmentService } from 'src/app/services/equipment.service';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.scss']
})
export class DeleteComponent  implements OnInit{


  id!:Number;

  constructor(private equipmentService:EquipmentService ,
     private activatedRoute:ActivatedRoute,
     private router:Router){}

  ngOnInit(): void {
    this.id = parseInt(this.activatedRoute.snapshot.paramMap.get("id") || '');
    if (this.id !=null)
      {
       this.equipmentService.deleteEquipment(this.id).subscribe((data) => {
          console.log("Delete the equipmenet");
          this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
       });
     }
  }










}
