import { Ticket } from 'src/app/models/ticket.model';
import { Component, OnInit } from '@angular/core';
import { TicketService } from 'src/app/services/ticket.service';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-user-tickets',
  templateUrl: './user-tickets.component.html',
  styleUrls: ['./user-tickets.component.scss']
})
export class UserTicketsComponent implements OnInit{
  userTickets: Ticket[] = [];

  constructor(
    private ticketService: TicketService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    const userId = this.authService.getUserIdFromJwt();
    if (userId !== null) { 
      this.loadUserTickets(userId);
    } else {
      console.error('User ID is null');
    }
  }


  loadUserTickets(userId: number) {
    this.ticketService.getUserTickets(userId).subscribe(
      (tickets) => {
        this.userTickets = tickets;
      },
      (error) => {
        console.error('Error fetching user tickets:', error);
      }
    );
  }

}
