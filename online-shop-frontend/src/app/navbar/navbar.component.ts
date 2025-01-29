import { CommonModule } from '@angular/common';
import { Component, computed, effect, HostListener, signal } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Router, RouterLink } from '@angular/router';
import { LoginUser, LoginUserState } from '../service/security/login-user.state';
import { CartService } from '../service/api/cart.service';
import { SearchProductComponent } from "./search-product/search-product.component";
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MatIconModule, CommonModule, RouterLink, FormsModule],
  templateUrl: './navbar.component.html',
  styles: ``
})
export class NavbarComponent {
  toggle = signal<boolean>(true);

  toggleMenu() {
   this.toggle.set(!this.toggle())
   console.log(this.toggle())
 }

 @HostListener('window:resize', ['$event'])
 onResize() : void {
  var screen = window.innerWidth
  if(screen < 768) {
    this.toggle.set(false);
  }else {
    this.toggle.set(true);
  }
 }

 user = signal<LoginUser | undefined>(undefined);
 userName = computed(() => this.user()?.name);
 
 cartTotalItem:number = 0;

 constructor(loginUser: LoginUserState, cartService: CartService, private router:Router) {
  effect(() => {
    this.user.set(loginUser.user());
    cartService.cartTotalAndPrice(this.user()?.id)
  })
  
  cartService.cartTotalItemAndPrice$.subscribe(result => {
    this.cartTotalItem = result.totalQuantity
  })
  
 }

 searchText:string = '';

 goCart() {
  this.router.navigate(['search/', this.searchText]);
 }

}
