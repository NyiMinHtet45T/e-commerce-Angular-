import { Routes } from "@angular/router";
import { AccountDetailComponent } from "./account-detail/account-detail.component";
import { AddressComponent } from "./address/address.component";
import { FavouriteProductsComponent } from "./favourite-products/favourite-products.component";
import { OrdersComponent } from "./orders/orders.component";
import { EditProductComponent } from "../../admin/edit-product/edit-product.component";

export const route:Routes = [
   {
      path: 'detail', component : AccountDetailComponent, title: 'detail'
   },
   {
      path: 'address/:isorder', component: AddressComponent, title: 'address'
   },
   {
      path: 'favorite', component: FavouriteProductsComponent, title: 'favorite'
   },
   {
      path: 'address', component: AddressComponent, title: 'address'
   },
   {
      path: 'orders', component: OrdersComponent, title: 'orders'
   },
   {
      path: 'create', component: EditProductComponent, title: 'create'
   },
   {
      path: '', redirectTo: 'members/profile/detail', pathMatch: 'full'
   }
]