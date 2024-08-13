import { AfterViewInit, Component, OnInit, ViewChild, ViewChildren } from '@angular/core';
import { SelectionModel } from '@angular/cdk/collections';
import {MatPaginator} from '@angular/material/paginator'
import { MatSort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { Ticket } from 'src/app/models/ticket.model';
import { Equipment } from 'src/app/models/equipment';
import { User } from 'src/app/models/user.model';
import { Fault } from 'src/app/models/fault.model';

import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { TicketService } from 'src/app/services/ticket.service';
import { EquipmentService } from 'src/app/services/equipment.service';
import { FaultService } from 'src/app/services/fault.service';


@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.scss']
})
export class TicketComponent implements AfterViewInit, OnInit{

  tickets: Ticket[]=[];
  user!:User;
  faults:Fault[]=[];
  equipment:Equipment[] = [];
  dataSource = new MatTableDataSource<Ticket>([]);
  displayColumns = ["username", "equipmentName","serialNumber","description","action"];
  selection = new SelectionModel<Ticket>(true, []);
  id!:number | null;



constructor(
  private ticketService :TicketService,
  private authService:AuthService,
  private equipmentService:EquipmentService,
  private faultService:FaultService,
  private userService:UserService
){}

@ViewChild(MatPaginator) paginator!: MatPaginator;
@ViewChild(MatSort) sort!: MatSort;


ngAfterViewInit(): void {
  this.dataSource.paginator = this.paginator;
  this.dataSource.sort = this.sort;
}

ngOnInit(): void {
  this.loadAllTicketsByUserId();
}

loadAllTicketsByUserId(){

  this.id = this.authService.getId();
  if(this.id != null){
    this.ticketService.getAllTecketByUserId(this.id).subscribe((data)=>{this.tickets= data});
    this.userService.getUserById(this.id).subscribe((data)=>{this.user =data})
    // this.equipmentService.getEquipmentById(this.t)
    // this.



  }


}


}
