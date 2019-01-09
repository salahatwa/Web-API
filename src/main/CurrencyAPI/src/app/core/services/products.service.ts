import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response, URLSearchParams } from '@angular/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { NgProgress } from 'ngx-progressbar';
import { UtilsService } from '../../shared/services/utils.service';
import { Config } from '../../shared/classes/app';
import { CrudService } from '../../shared/services/crud.service';
import { REQUEST_OPTIONS_DEFAULT } from '../../shared/services/request-options.default';
import { MC40200 } from '../classes/MC40200';


@Injectable()
export class ProductsService extends CrudService<MC40200, number>{
  private _productsUrl = `${new Config().api}/currencies`;
  private _headers = this._utils.makeHeaders({ withToken: true });

  constructor(
    private _utils: UtilsService,
    private _http: Http,
    private _router: Router,
    protected _progress: NgProgress
  ) {
    super(`${new Config().api}/currencies`, _http,REQUEST_OPTIONS_DEFAULT,_progress);
   }

  
  gotoProductsPage()
  {
    this._router.navigate(['/products']);
  }

  

}
