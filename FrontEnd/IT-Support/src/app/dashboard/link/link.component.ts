import { Component, OnInit } from '@angular/core';
import { TicketService } from '../../services/ticket.service';
import { Ticket } from '../../models/ticket.model';
import { TicketDetails } from '../../models/ticketDetails.model';
import { TechnicianService } from '../../services/technician.service';
import { Technician } from '../../models/technician.model';
import { MatTableDataSource } from '@angular/material/table';




@Component({
  selector: 'app-link',
  templateUrl: './link.component.html',
  styleUrls: ['./link.component.scss']
})
export class LinkComponent {

  displayedColumns: string[] = ['id', 'creationDate', 'status', 'description', 'user', 'fault', 'equipment', 'technician'];
  dataSource!: MatTableDataSource<TicketDetails>;
  technicians: Technician[] = [];

  constructor(
    private ticketService: TicketService,
    private technicianService: TechnicianService
  ) { }

  ngOnInit() {
    this.loadTickets();
    this.loadTechnicians();
  }

  loadTickets() {
    this.ticketService.getAllTickets().subscribe(
      (tickets) => {
        this.dataSource = new MatTableDataSource(tickets);
      },
      (error) => console.error('Error loading tickets:', error)
    );
  }

  loadTechnicians() {
    this.technicianService.getTechnicians().subscribe(
      (technicians) => {
        this.technicians = technicians;
      },
      (error) => console.error('Error loading technicians:', error)
    );
  }

  assignTechnician(ticketId: number, technicianId: number) {
    this.ticketService.assignTicketToTechnician(ticketId, technicianId).subscribe(
      (updatedTicket) => {
        // Refresh the tickets list after assigning
        this.loadTickets();
      },
      (error) => console.error('Error assigning technician:', error)
    );
  }
}
