import { Component, OnInit } from '@angular/core';
import { PessoasService } from './services/pessoas.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title = 'balance-control';

  constructor(private pessoaService: PessoasService) {}
  ngOnInit(): void {
    // this.pessoaService.addPessoa({ nome: 'Leandro' });
    // this.pessoaService.addPessoa({ nome: 'Rayssa' });



  }
}
