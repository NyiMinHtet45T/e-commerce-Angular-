import { Component, computed, effect, input, signal } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { CartService } from '../../../../service/api/cart.service';
import { LoginUserState } from '../../../../service/security/login-user.state';

@Component({
  selector: 'app-cart-card',
  standalone: true,
  imports: [MatIconModule],
  templateUrl: './cart-card.component.html',
  styles: ``
})
export class CartCardComponent {

  userId = signal<number | undefined>(undefined)
  cart = signal<any>(undefined);
  cartItem = computed(() => this.cart()?.cartItemResponses);

  constructor(private cartService:CartService,loginUser:LoginUserState) {
        this.userId.set(loginUser.user()?.id);
        this.getCartByUserId();
  }

  getCartByUserId = () => {
    if(this.userId()) {
      this.cartService.getCartByUserId(this.userId()).subscribe(result => {
        this.cart.set(result);
      })
    }
  }

  handleAddingQuantity = (cartItemId: number,currentQuantity: number , isAdd:boolean) => {
    if(currentQuantity > 0) {
        var totalQuantity = isAdd ? currentQuantity + 1 : currentQuantity - 1;
        this.cartService.addCartQuantity(cartItemId,totalQuantity).subscribe(result => {
          this.getCartByUserId();
          this.cartService.cartTotalAndPrice(this.userId());
        });
    }else {
      this.handleDelete(cartItemId);
    }
  }

  handleDelete = (cartItemId : number) => {
    this.cartService.removeCartItemFromCart(cartItemId,this.userId()).subscribe(result => {
      this.getCartByUserId();
      this.cartService.cartTotalAndPrice(this.userId());
    })
  }


}
