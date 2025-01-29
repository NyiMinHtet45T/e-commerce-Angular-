import { Routes } from '@angular/router';
import { AnonymousComponent } from './pages/anonymous/anonymous.component';
import { SiginComponent } from './pages/anonymous/sigin/sigin.component';
import { SignupComponent } from './pages/anonymous/signup/signup.component';
import { HomeComponent } from './pages/member/home/home.component';
import { ProductDetailComponent } from './pages/member/product/product-detail/product-detail.component';
import { CartComponent } from './pages/member/cart/cart.component';
import { ProfileComponent } from './pages/member/profile/profile.component';
import { EditProductComponent } from './pages/admin/edit-product/edit-product.component';
import { SearchProductComponent } from './navbar/search-product/search-product.component';
import { FilterCategoryComponent } from './pages/member/category/filter-category/filter-category.component';

export const routes: Routes = [
   {
      path: 'anonymous', component: AnonymousComponent, children: [
         {
            path: 'signin', component: SiginComponent, title: 'signin'
         },
         {
            path: 'signup', component: SignupComponent, title: 'signUp'
         },
      ]
   },
   {
      path: 'home', component: HomeComponent, title: 'home' 
   },
   {
      path: 'filter-category/:name', component: FilterCategoryComponent, title: 'filter-category'
   },
   {
      
      path: 'product/detail/:id', component: ProductDetailComponent, title: 'detail'
      
   },
   {
      path: 'cart', component: CartComponent, title: 'cart'
   },
   {
      path: 'admin/:productId', component: EditProductComponent, title: 'edit-Product'
   },
   {
      path: 'members/profile', component: ProfileComponent,
      loadChildren: () => import('./pages/member/profile/profile.routes').then(m => m.route)
   },
   {
      path: 'search/:name', component: SearchProductComponent, title: 'search-product'
   },
   {
      path: '', redirectTo: 'home', pathMatch: 'full'
   }
];
