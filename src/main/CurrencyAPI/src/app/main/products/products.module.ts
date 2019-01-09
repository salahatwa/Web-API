import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/modules/shared.module';
import { ProductsRoutingModule } from './products-routing.module';
import { ProductsService } from '../../core/services/products.service';

@NgModule({
  imports: [
    SharedModule,
    ProductsRoutingModule
  ],
  providers: [ProductsService],
  declarations: []
})
export class ProductsModule { }
