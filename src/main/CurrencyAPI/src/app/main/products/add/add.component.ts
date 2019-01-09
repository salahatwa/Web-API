import { Component, OnInit, OnDestroy, Output, EventEmitter } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Subscription } from 'rxjs/Subscription';
import { isArray } from 'lodash';
import { ProductsService } from '../../../core/services/products.service';
import { UtilsService } from '../../../shared/services/utils.service';
import { MC40200 } from '../../../core/classes/MC40200';

declare var numeral: any;
@Component({
  selector: 'app-dam-pharm-products-add',
  templateUrl: './add.component.html',
  styles: []
})
export class AddComponent implements OnInit, OnDestroy {
  private _sub: Subscription = undefined;
  private _typeSub: Subscription = undefined;

  @Output('update')
  update: EventEmitter<MC40200> = new EventEmitter<MC40200>();

  currency:MC40200;
  

  constructor(
    private _productsService: ProductsService,
    private _utils: UtilsService,
    public translate: TranslateService
  ) { }

  ngOnInit() {
    this.initProduct();
  }

  ngOnDestroy() {
    this._utils.unsubscribeSub(this._sub);
  }

  onSubmit() {
    this._utils.unsubscribeSub(this._sub);
 
    this._sub = this._productsService.save(this.currency)
      .subscribe(data => {
          this.update.emit(data);
          this.initProduct();
      });
  }

  onKeyup(e: any) {
    e.target.value = numeral(e.target.value).format(this._utils.format);
  }

  initProduct() {
    this.currency = new MC40200();
  }

}
