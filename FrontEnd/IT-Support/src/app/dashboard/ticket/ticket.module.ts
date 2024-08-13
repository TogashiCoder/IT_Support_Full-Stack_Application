import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TicketRoutingModule } from './ticket-routing.module';
import { TicketComponent } from './ticket.component';
import { UserTicketsComponent } from './user-tickets/user-tickets.component';


@NgModule({
  declarations: [
    TicketComponent,
    UserTicketsComponent
  ],
  imports: [
    CommonModule,
    TicketRoutingModule
  ]
})
export class TicketModule { }
