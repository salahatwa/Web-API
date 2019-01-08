import { NgModule } from '@angular/core';
import { LoginRoutingModule } from './login/login-routing.module';
import { ProductsRoutingModule } from './products/products-routing.module';

@NgModule({
  imports: [],
  declarations: [],
  exports: [
    LoginRoutingModule,
    ProductsRoutingModule
  ]
})
export class MainRoutingModule { }
