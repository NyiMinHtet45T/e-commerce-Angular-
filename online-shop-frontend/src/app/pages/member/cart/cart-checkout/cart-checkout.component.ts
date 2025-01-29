import { Component, computed, signal } from '@angular/core';
import { CartService } from '../../../../service/api/cart.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart-checkout',
  standalone: true,
  imports: [],
  templateUrl: './cart-checkout.component.html',
  styles: ``
})
export class CartCheckoutComponent {

  cartTotalQuantityAndPrice = signal<any>(undefined);
  cartTotalQuantity = computed(() => this.cartTotalQuantityAndPrice()?.totalQuantity);
  cartTotalPrice = computed(() => this.cartTotalQuantityAndPrice()?.totalPrice);
  
  constructor(private cartService:CartService, private router:Router) {
    cartService.cartTotalItemAndPrice$.subscribe(result => {
      this.cartTotalQuantityAndPrice.set(result);
    })
  }

  goOrder() {
    this.router.navigate(['members/profile/address/', 'order'])
  }


}
