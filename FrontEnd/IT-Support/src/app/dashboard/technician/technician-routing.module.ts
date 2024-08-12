import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TechnicianComponent } from './technician.component';
import { AddComponent } from './add/add.component';
import { ViewComponent } from './view/view.component';
import { EditComponent } from './edit/edit.component';
import { DeleteComponent } from './delete/delete.component';
import { PageNotFoundComponent } from 'src/app/page-not-found/page-not-found.component';

const routes: Routes = [
  { path: '', component: TechnicianComponent },
  { path: 'add', component: AddComponent },
  {path:'view/:id',component:ViewComponent},
  {path:'edit/:id',component:EditComponent},
  {path:'delete/:id',component:DeleteComponent},
  {path:'**',component:PageNotFoundComponent}
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TechnicianRoutingModule { }
