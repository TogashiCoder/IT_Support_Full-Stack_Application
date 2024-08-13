import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ticket } from '../models/ticket.model';



@Injectable({
  providedIn: 'root'
})
export class TicketService {
  
  private apiUrl = 'your-api-url/tickets';

  constructor(private http: HttpClient) {}

  getUserTickets(userId: number): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/user/${userId}`);
  }
}
