import { AfterViewInit, Component, OnInit, ViewChild, ViewChildren } from '@angular/core';
import { SelectionModel } from '@angular/cdk/collections';
import {MatPaginator} from '@angular/material/paginator'
import { MatSort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { Ticket } from 'src/app/models/ticket.model';

import { AuthService } from 'src/app/services/auth.service';
import { TicketService } from 'src/app/services/ticket.service';
import { TicketDetails } from 'src/app/models/ticketDetails.model';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent {

  tickets: TicketDetails[]= [];


  dataSource = new MatTableDataSource<TicketDetails>([]);
  displayColumns = ["username", "equipmentName","serialNumber","description","Status"];
  selection = new SelectionModel<Ticket>(true, []);
  id!:number | null;



constructor(
  private ticketService :TicketService,
  private authService:AuthService){}

@ViewChild(MatPaginator) paginator!: MatPaginator;
@ViewChild(MatSort) sort!: MatSort;


ngAfterViewInit(): void {
  this.dataSource.paginator = this.paginator;
  this.dataSource.sort = this.sort;
}

ngOnInit(): void {
  this.loadAllTicketsByUserId();
}

loadAllTicketsByUserId() {
  this.id = this.authService.getId();
  if (this.id != null) {
    this.ticketService.getAllTecketsByUserId(this.id).subscribe((data) => {
      this.tickets = data;
      this.dataSource.data = this.tickets;
    });
  }
}





createNewTicket(){}


}
