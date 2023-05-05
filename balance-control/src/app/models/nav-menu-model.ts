import { Routes } from '@angular/router';
import { OrcamentoComponent } from '../components/orcamento/orcamento.component';

export class NavMenuItem {
  path:string="";
  text:string="";
  component: any
}

export const navMenu:NavMenuItem[] = [
  { path: 'home', component: OrcamentoComponent, text: 'Home' },
];
