import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { navMenu } from './models/nav-menu-model';

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
