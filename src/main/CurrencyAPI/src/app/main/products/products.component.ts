import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { TranslateService } from '@ngx-translate/core';
import { isArray } from 'lodash';
import { ProductsService } from '../../core/services/products.service';
import { UtilsService } from '../../shared/services/utils.service';
import { CurrencyParam } from '../../core/classes/ParamInterfaces';
import { GenericPagination } from '../../core/classes/generic-pagination';
import { MC40200 } from '../../core/classes/MC40200';

@Component({
  selector: 'app-dam-pharm-products',
  templateUrl: './products.component.html',
  styles: []
})
export class ProductsComponent extends GenericPagination<MC40200> implements OnInit, OnDestroy {
  private _sub: Subscription = undefined;
  

  constructor(
    private _productService: ProductsService,
    private _utils: UtilsService,
    public translate: TranslateService
  ) { 
    super(_productService);
  }

  ngOnInit() {
    this.loadCurrencies();
  }

  ngOnDestroy() {
    this._utils.unsubscribeSub(this._sub);
  }


  loadCurrencies() {
    const param = new CurrencyParam();
    param.page = this.page;
    this.setPageParam(param);
    this.loadPage();
  }



  onUpdate(currency: MC40200) {
    this.results.push(currency);
  }

}
