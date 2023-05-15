export class BaseService {
  protected ENTITY = '';
  constructor(entity: string) {
    this.ENTITY = entity;
  }
}
