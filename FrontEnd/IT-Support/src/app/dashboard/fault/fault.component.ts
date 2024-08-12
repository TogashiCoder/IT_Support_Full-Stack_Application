import { AfterViewInit, Component, OnInit, ViewChild, ViewChildren } from '@angular/core';
import { SelectionModel } from '@angular/cdk/collections';
import {MatPaginator} from '@angular/material/paginator'
import { MatSort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { Fault } from 'src/app/models/fault.model';
import { FaultService } from 'src/app/services/fault.service';

@Component({
  selector: 'app-fault',
  templateUrl: './fault.component.html',
  styleUrls: ['./fault.component.scss']
})
export class FaultComponent  implements AfterViewInit,OnInit{

  faults:Fault[]=[];
  dataSource = new MatTableDataSource<Fault>([]);
  displayColumns = ["id","description","action"];
  selection = new SelectionModel<Fault>(true, []);

  constructor(private faultService:FaultService){}

@ViewChild(MatPaginator) paginator!: MatPaginator;
@ViewChild(MatSort) sort!: MatSort;


ngAfterViewInit(): void {
  this.dataSource.paginator = this.paginator;
  this.dataSource.sort = this.sort;
}

ngOnInit() {
  this.loadFault();
}




loadFault(){
  this.faultService.getallFaults().subscribe(
    (data: Fault[])=>{
      this.faults =data;
      this.dataSource.data =this.faults;
    },
    (error)=> console.log('Error fetching faults:',error)
  );
}




}
