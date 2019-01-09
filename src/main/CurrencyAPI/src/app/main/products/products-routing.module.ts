import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ProductsComponent } from './products.component';
import { SharedModule } from '../../shared/modules/shared.module';
import { AuthGuard } from '../../core/services/auth.guard';
import { AddComponent } from './add/add.component';

@NgModule({
  imports: [
    SharedModule,
    RouterModule.forChild([
      {
        path: 'products', canActivateChild: [AuthGuard],
        children: [
          { path: '', component: ProductsComponent }
        ]
      },
    ])
  ],
  declarations: [
    ProductsComponent,
    AddComponent
  ],
  providers: [AuthGuard],
  exports: [
    RouterModule,
    ProductsComponent,
    AddComponent
  ]

})
export class ProductsRoutingModule { }
