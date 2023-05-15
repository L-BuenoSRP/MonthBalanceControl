import { Injectable } from '@angular/core';
import { BaseService } from './base-service';
import { NgxIndexedDBService } from 'ngx-indexed-db';
import { Pessoas } from '../models/pessoa-model';

@Injectable({
  providedIn: 'root',
})
export class PessoasService extends BaseService {
  constructor(private dbService: NgxIndexedDBService) {
    super('pessoas');
  }

  addPessoa(pessoa: Pessoas) {
    this.dbService
      .add(this.ENTITY, pessoa)
      .subscribe((key: any) => {
        console.log('key: ', key);
      });
  }

  // this.dbService
  //   .add('pessoas', {
  //     nome: `Bruce Wayne`,
  //   })
  //   .subscribe((key: any) => {
  //     console.log('key: ', key);
  //   });

  // this.dbService
  //   .bulkAdd('pessoas', [
  //     {
  //       nome: `charles number ${Math.random() * 10}`,
  //     },
  //     {
  //       nome: `charles number ${Math.random() * 10}`,
  //     },
  //   ])
  //   .subscribe((result) => {
  //     console.log('result: ', result);
  //   });
  // // this.dbService.bulkDelete('pessoas', [5, 6]).subscribe((result) => {
  // //   console.log('result: ', result);
  // // });
  // this.dbService.bulkGet('pessoas', [1, 3, 5]).subscribe((result: any) => {
  //   console.log('results: ', result);
  // });
  // this.dbService
  //   .update('pessoas', {
  //     id: 1,
  //     nome: 'Luke Skywalker',
  //   })
  //   .subscribe((storeData) => {
  //     console.log('storeData: ', storeData);
  //   });
  // this.dbService.getByKey('pessoas', 1).subscribe((pessoas) => {
  //   console.log(pessoas);
  // });
  // this.dbService.getAll('pessoas').subscribe((pessoass) => {
  //   console.log(pessoass);
  // });
  // this.dbService
  //   .getByIndex('pessoas', 'nome', 'Dave')
  //   .subscribe((pessoas) => {
  //     console.log(pessoas);
  //   });
  // const storeSchema: ObjectStoreMeta = {
  //   store: 'pessoas',
  //   storeConfig: { keyPath: 'id', autoIncrement: true },
  //   storeSchema: [
  //     { name: 'nome', keypath: 'nome', options: { unique: false } },
  //   ],
  // };

  // this.dbService.createObjectStore(storeSchema);
  // this.dbService.deleteDatabase().subscribe((deleted) => {
  //   console.log('Database deleted successfully: ', deleted);
  // });
}
