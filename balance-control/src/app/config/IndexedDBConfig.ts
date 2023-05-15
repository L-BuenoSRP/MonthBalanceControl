import { DBConfig } from 'ngx-indexed-db';
import { Pessoas } from '../models/pessoa-model';
function migrationFactory() {
  // The animal table was added with version 2 but none of the existing tables or data needed
  // to be modified so a migrator for that version is not included.
  return {
    // 1: (db: IDBDatabase, transaction: IDBTransaction) => {
    //   const store = transaction.objectStore('people');
    //   store.createIndex('country', 'country', { unique: false });
    // },
    // 3: (db: IDBDatabase, transaction: IDBTransaction) => {
    //   const store = transaction.objectStore('people');
    //   store.createIndex('age', 'age', { unique: false });
    // },
  };
}
export const dbConfig: DBConfig = {
  name: 'MyDb',
  version: 1,
  objectStoresMeta: [
    {
      store: 'pessoas',
      storeConfig: { keyPath: 'id', autoIncrement: true },
      storeSchema: [
        { name: 'nome', keypath: 'nome', options: { unique: true } },
      ],
    },
    {
      store: 'receitas_recorrentes',
      storeConfig: { keyPath: 'id', autoIncrement: true },
      storeSchema: [
        { name: 'descricao', keypath: 'descricao', options: { unique: false } },
        { name: 'dia', keypath: 'dia', options: { unique: false } },
        { name: 'repeticao_tipo', keypath: 'repeticao_tipo', options: { unique: false } },
        { name: 'valor_estimado', keypath: 'valor_estimado', options: { unique: false } },
        { name: 'id_pessoa', keypath: 'id_pessoa', options: { unique: false } },
      ],
    },
    {
      store: 'receitas_ocorrencias',
      storeConfig: { keyPath: 'id', autoIncrement: true },
      storeSchema: [
        { name: 'data', keypath: 'data', options: { unique: false } },
        { name: 'valor_recebido', keypath: 'valor_recebido', options: { unique: false } },
        { name: 'id_receita_recorrente', keypath: 'id_receita_recorrente', options: { unique: false } },
        { name: 'id_pessoa', keypath: 'id_pessoa', options: { unique: false } },
      ],
    },
    {
      store: 'cartoes',
      storeConfig: { keyPath: 'id', autoIncrement: true },
      storeSchema: [
        { name: 'final_numero', keypath: 'final_numero', options: { unique: true } },
        { name: 'vencimento', keypath: 'vencimento', options: { unique: false } },
        { name: 'id_pessoa', keypath: 'id_pessoa', options: { unique: false } },
      ],
    },
    {
      store: 'valor_cartoes_mes',
      storeConfig: { keyPath: 'id', autoIncrement: true },
      storeSchema: [
        { name: 'valor', keypath: 'valor', options: { unique: false } },
        { name: 'mes_vencimento', keypath: 'mes_vencimento', options: { unique: false } },
        { name: 'id_cartao', keypath: 'id_cartao', options: { unique: false } },
      ],
    },
    {
      store: 'ocorrencias_cartao',
      storeConfig: { keyPath: 'id', autoIncrement: true },
      storeSchema: [
        { name: 'descricao', keypath: 'descricao', options: { unique: false } },
        { name: 'valor_total', keypath: 'valor_total', options: { unique: false } },
        { name: 'numero_parcelas', keypath: 'numero_parcelas', options: { unique: false } },
        { name: 'mes_inicio', keypath: 'mes_inicio', options: { unique: false } },
        { name: 'id_cartao', keypath: 'id_cartao', options: { unique: false } },
      ],
    },
    {
      store: 'parcelas_ocorrencias_cartao',
      storeConfig: { keyPath: 'id', autoIncrement: true },
      storeSchema: [
        { name: 'data', keypath: 'data', options: { unique: false } },
        { name: 'valor', keypath: 'valor', options: { unique: false } },
        { name: 'numero_parcela', keypath: 'numero_parcela', options: { unique: false } },
        { name: 'id_ocorrencias_cartao', keypath: 'id_ocorrencias_cartao', options: { unique: false } },
      ],
    },
    {
      store: 'despesas_recorrentes',
      storeConfig: { keyPath: 'id', autoIncrement: true },
      storeSchema: [
        { name: 'descricao', keypath: 'descricao', options: { unique: false } },
        { name: 'dia', keypath: 'dia', options: { unique: false } },
        { name: 'repeticao_tipo', keypath: 'repeticao_tipo', options: { unique: false } },
        { name: 'valor_estimado', keypath: 'valor_estimado', options: { unique: false } },
        { name: 'id_pessoa', keypath: 'id_pessoa', options: { unique: false } },

      ],
    },
    {
      store: 'despesas_ocorrencias',
      storeConfig: { keyPath: 'id', autoIncrement: true },
      storeSchema: [
        { name: 'data', keypath: 'data', options: { unique: false } },
        { name: 'valor', keypath: 'valor', options: { unique: false } },
        { name: 'id_valor_cartoes_mes', keypath: 'id_valor_cartoes_mes', options: { unique: true } },
        { name: 'id_pessoa', keypath: 'id_pessoa', options: {  unique: false } },
      ],
    },
    {
      store: 'atualizacao-conta',
      storeConfig: { keyPath: 'id', autoIncrement: true },
      storeSchema: [
        { name: 'data', keypath: 'data', options: { unique: false } },
        { name: 'mes_atualizacao', keypath: 'mes_atualizacao', options: { unique: false } },
        { name: 'valor', keypath: 'valor', options: { unique: false } },
        { name: 'id_pessoa', keypath: 'id_pessoa', options: { unique: false } },
      ],
    },
    // resta mapear recebimentos(salario, entre outros), tambem ocorrencias de certas despesas importantes no debito/ dinheiro
    // tambem mapeamento do valor atual de cada cartão para determinado mes, este valor sera comparado com os lançamentos das despesas
    // feitos, ja que o intuito é registrar as despesas mais recorrentes e de mais peso
    // avaliar o uso de id para busca de relacionamentos ao inves de valor chave que de para ser utilizado na busca com os nomes unicos
    // de propriedades relacionais

  ],
  // provide the migration factory to the DBConfig
  migrationFactory,
};


//Itens necessarios para o aplicativo

/*Entidade Pessoa - Para definição de quem é os gastos/quem esta pagando algo
Entidade Contas - Definição de contas frequentes a pagar, podendo ser mensalmente ou outra frequencia, baseando-se em um valor medio_
,que futuramente podera ser calculado com media para que fique mais fiel ao valor final
Entidade Recebimentos - Definição de recebimentos frequentes, podendo ser mensalmente ou outra frequenscia, baseando-se em um valor medio_
,que futuramente podera ser calculado com media para que fique mais fiel ao valor final
Entidade Cartoes - Definição de cartões que devem ser pagos mensalmente
Entidade Cartoes_Compras - Definição macro das contas dos cartões, com relacionamento com a Cartoes e relacionamento com Cartoes_Compras_Parcelas_
,onde tera a informação de quantidade de parcelas de uma compra, valor sem parcelar e qual o mes da primeira parcela
Entidade Cartoes_Compras_Parcelas - Definição das parcelas de uma compra, sequencial ao mes subsequente, com relacionamento para Cartoes e Cartoes_Compras, contendo o numero da parcelas de cada compra_
,onde tera a informação do numero da parcela atual, pode ter o numero da parcela final, valor da parcela e data(mes)
Entidade Cartoes_Fechamentos_Mes, contera o valor somado de lançamentos do cartão para determinado mes, com relacionamentos para Cartoes, onde será feito a somatoria de parcelas de um determinado mes em um campo valor_parcelas_
,considerando a soma das parcelas registradas e o valor atual do mes que pode ser atualizado com frequencia, o mesmo podera ser comparado com os valores de parcelas para que seja analisado quanto de gasto do cartão é apenas do mes_
e qual percentual disso foi
Entidade Creditos - Creditos registrados a partir de entidades acima(Recebimentos) e seus valores(conitnuar a definir relacionamentos e regras)
Entidade Debitos - Debitos registrados a partir de entidades acima(Cartoes_Fechamentos_Mes, Contas) e seus valores(conitnuar a definir relacionamentos e regras)


???Entidade Saldo_Atualizacao??? - Possibilidade de funcionamento de uma entidade saldo, que ligue com pessoa, onde tera um controle de data ou outra alternativa, como status,
 que registre momentos do saldo e consiga trabalhar com os dados de debitos e creditos ocorridos ao longo dos periodos e atrualize quaisquer estimativa de gasto futuro
 , a partir das entidades de ocorrencia

Entides de ocorrencia mensal, como Cartoes_Compras_Parcelas, Cartoes_Fechamentos_Mes,
deverão ter o campo mes_ano, no formato MM/yyyy, para que seja feito buscas otimizadas de itens de cada mes caso necessario

Pensar em como e quais campos aplicar para que as entidades Creditos e Debitos serão controladas para otimizar os calculos mes a mes
*/
