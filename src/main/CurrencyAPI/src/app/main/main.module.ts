import { NgModule } from '@angular/core';
import { LoginModule } from './login/login.module';
import { MainRoutingModule } from './main-routing.module';
import { RouterModule } from '@angular/router';
import { ProductsModule } from './products/products.module';

@NgModule({
  imports: [],
  declarations: [],
  exports: [
    MainRoutingModule,
    LoginModule,
    ProductsModule
  ]
})
export class MainModule { }
