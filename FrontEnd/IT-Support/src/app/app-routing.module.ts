import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from 'src/app/login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AuthGuard } from './guiard/AuthGuard ';


// const routes: Routes = [
//   {path:'login', component:LoginComponent},
//   {
//      path: 'dashboard', loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule)
//      canActivate: [AuthGuard]
//   },
//   { path: '', redirectTo: '/login', pathMatch: 'full' },
//   { path: 'logout', component: LogoutComponent }

// ];
const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'dashboard',
    loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule),
    canActivate: [AuthGuard]
  },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'logout', component: LogoutComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
