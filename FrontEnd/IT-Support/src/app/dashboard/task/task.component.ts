import { Component, OnInit } from '@angular/core';
import { TicketService } from 'src/app/services/ticket.service';
import { TicketDetails } from 'src/app/models/ticketDetails.model';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent implements OnInit {
  dataSource!: MatTableDataSource<TicketDetails>;
  displayedColumns: string[] = ['id', 'creationDate', 'description', 'status', 'user', 'fault', 'equipment'];
  statuses: string[] = ['ASSIGNED', 'IN_PROGRESS', 'COMPLETED', 'CLOSED', 'CANCELLED'];

  technicianId!:number | null;


  constructor(private ticketService: TicketService, private authService:AuthService) {}

  ngOnInit(): void {
    this.loadTickets();
  }

  loadTickets(): void {
    // if(this.technicianId)
    this.technicianId = this.authService.getId();
    if (this.technicianId != null)
    console.log(this.technicianId)
    if (this.technicianId != null) {
    this.ticketService.getAllTicketsByTechnicianId(this.technicianId).subscribe(
      (tickets: TicketDetails[]) => {
        this.dataSource = new MatTableDataSource(tickets);
      },
      error => console.error('Error fetching tickets:', error)
    );
  }
  }

  updateStatus(ticket: TicketDetails, newStatus: string): void {
    this.ticketService.updateTicketStatus(ticket.id, newStatus).subscribe(
      updatedTicket => {
        console.log(`Updated ticket ${updatedTicket.id} status to ${updatedTicket.status}`);
      },
      error => console.error('Error updating ticket status:', error)
    );
  }

  isStatusDisabled(status: string): boolean {
    return ['COMPLETED', 'CLOSED', 'CANCELLED'].includes(status);
  }
}

//v
