import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TicketComponent } from './ticket.component';
import { ViewComponent } from './view/view.component';
import { AddComponent } from './add/add.component';
import { TaskComponent } from '../task/task.component';

const routes: Routes = [
  { path: '', component: TicketComponent },
  { path: 'tickets', component: ViewComponent },
  {path:'add',component:AddComponent},
  {path:'task', component:TaskComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TicketRoutingModule { }
