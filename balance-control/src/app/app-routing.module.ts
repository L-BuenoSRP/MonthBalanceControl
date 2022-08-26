import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { navMenu } from './models/navMenu-model';


const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'home',
  },
];

routes.push.apply(routes, navMenu);

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
