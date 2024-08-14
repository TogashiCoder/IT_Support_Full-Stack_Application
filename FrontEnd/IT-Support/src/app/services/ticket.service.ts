import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ticket } from '../models/ticket.model';
import { TicketDetails } from '../models/ticketDetails.model';


@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private apiUrl = 'http://localhost:8080/api/tickets';

  constructor(private http: HttpClient) {}





  // create New Ticket
  createNewTicket(ticket: Ticket): Observable<Ticket> {
    return this.http.post<Ticket>(this.apiUrl +"/user", ticket);
  }

    // Assign a technician to a ticket
  assignTicketToTechnician(ticketId: number, technicianId: number): Observable<Ticket> {
    return this.http.put<Ticket>(`${this.apiUrl}/admin/${ticketId}/technician/${technicianId}`, {});
  }

  // Get all Tickets by Technician ID
  getAllTicketsByTechnicianId(technicianId: number): Observable<TicketDetails[]> {
    return this.http.get<TicketDetails[]>(`${this.apiUrl}/technicians/${technicianId}/details`);
  }

  // Get all Tickets (assuming this returns TicketDetails)
  getAllTickets(): Observable<TicketDetails[]> {
    return this.http.get<TicketDetails[]>(this.apiUrl + "/with-details");
  }


  getAllTecketsByUserId(userId:number):Observable<TicketDetails[]>
  {
    return this.http.get<TicketDetails[]>(`${this.apiUrl}/user/${userId}`);
  }


 // Update Ticket Status
 updateTicketStatus(ticketId: number, updateStatus: string): Observable<Ticket> {
  return this.http.put<Ticket>(`${this.apiUrl}/${ticketId}/status?status=${updateStatus}`, {});
}




}
