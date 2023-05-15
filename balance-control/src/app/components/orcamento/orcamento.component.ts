import { Component, OnInit } from '@angular/core';
import { NgxIndexedDBService, ObjectStoreMeta } from 'ngx-indexed-db';

@Component({
  selector: 'app-orcamento',
  templateUrl: './orcamento.component.html',
  styleUrls: ['./orcamento.component.scss'],
})
export class OrcamentoComponent implements OnInit {
  constructor(private dbService: NgxIndexedDBService) {}

  mes: string = '';
  folders: any[] = [
    {
      nome: 'Photos',
      updated: new Date('1/1/16'),
    },
    {
      nome: 'Recipes',
      updated: new Date('1/17/16'),
    },
    {
      nome: 'Work',
      updated: new Date('1/28/16'),
    },
  ];
  notes: any[] = [
    {
      nome: 'Vacation Itinerary',
      updated: new Date('2/20/16'),
    },
    {
      nome: 'Kitchen Remodel',
      updated: new Date('1/18/16'),
    },
  ];

  ngOnInit(): void {
    this.mes = new Date().toLocaleString('pt-br', { month: 'long' });
  }

  add() {

  }

  get() {}



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
