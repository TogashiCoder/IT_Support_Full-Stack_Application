import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ticket } from '../models/ticket.model';



@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private apiUrl = 'http://localhost:8080/api/tickets/users';

  constructor(private http: HttpClient) {}





  // create New Ticket
  createNewTicket(ticket: Ticket): Observable<Ticket> {
    return this.http.post<Ticket>(this.apiUrl, ticket);
  }

    // Get all Tickets by User ID
  getAllTecketByUserId(userId: number): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/users/${userId}`);
  }

  // Get all Tickets by Technician ID
  getAllTicketsByTechnicianId(technicianId: number): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/technicians/${technicianId}`);
  }


}
