import { AfterViewInit, Component, ViewChild, ViewChildren } from '@angular/core';
import { SelectionModel } from '@angular/cdk/collections';
import {MatPaginator} from '@angular/material/paginator'
import { MatSort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { Technician } from 'src/app/models/technician.model';
import { TechnicianService } from 'src/app/services/technician.service';



@Component({
  selector: 'app-technician',
  templateUrl: './technician.component.html',
  styleUrls: ['./technician.component.scss']
})
export class TechnicianComponent implements AfterViewInit {

technician:Technician[]= [];
dataSource = new MatTableDataSource<Technician>([]);
displayColumns = ["username", "email","action"];
selection = new SelectionModel<Technician>(true, []);

constructor(private technicianService:TechnicianService){}


@ViewChild(MatPaginator) paginator!: MatPaginator;
@ViewChild(MatSort) sort!: MatSort;


ngAfterViewInit(): void {
  this.dataSource.paginator = this.paginator;
  this.dataSource.sort = this.sort;
}

ngOnInit() {
  this.loadTechnicians();
}

loadTechnicians(){

  this.technicianService.getTechnicians().subscribe(
    (data: Technician[])=>{
      this.technician =data;
      this.dataSource.data =this.technician;
    },
    (error)=> console.log('Error fetching technician:',error)
  );

}

}
