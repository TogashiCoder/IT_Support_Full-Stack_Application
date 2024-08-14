import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { LinkComponent } from './link/link.component';

const routes: Routes = [
  {
    path: '',
    component: DashboardComponent,
    children: [
      {
        path: 'equipment',
        loadChildren: () => import('./equipment/equipment.module').then(m => m.EquipmentModule)
      },
      {
        path:'link',component:LinkComponent
      },
      { path: 'user',
         loadChildren: () => import('./user/user.module').then(m => m.UserModule)
      },
      { path: 'technician',
         loadChildren: () => import('./technician/technician.module').then(m => m.TechnicianModule)
      },
      { path: 'fault',
         loadChildren: () => import('./fault/fault.module').then(m => m.FaultModule)
      },
      { path: 'ticket',
         loadChildren: () => import('./ticket/ticket.module').then(m => m.TicketModule)
      },
    ]
  },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
