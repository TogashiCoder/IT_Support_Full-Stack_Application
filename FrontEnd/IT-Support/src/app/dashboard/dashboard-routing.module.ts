import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';

const routes: Routes = [
  {
    path: '',
    component: DashboardComponent,
    children: [
      {
        path: 'equipment',
        loadChildren: () => import('./equipment/equipment.module').then(m => m.EquipmentModule)
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
    ]
  },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
