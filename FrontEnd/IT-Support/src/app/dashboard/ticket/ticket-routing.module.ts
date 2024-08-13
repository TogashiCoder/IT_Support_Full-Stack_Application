import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TicketComponent } from './ticket.component';
import { UserTicketsComponent } from './user-tickets/user-tickets.component';


const routes: Routes = [
  { path: '', component: TicketComponent },
  { path: 'user', component: UserTicketsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TicketRoutingModule { }
