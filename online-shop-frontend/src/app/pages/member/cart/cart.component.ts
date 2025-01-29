import { Component, effect, input } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { CartCardComponent } from "./cart-card/cart-card.component";
import { CartCheckoutComponent } from "./cart-checkout/cart-checkout.component";
import { TitleContentsComponent } from "../../../widgets/title-contents/title-contents.component";

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [MatIconModule, CartCardComponent, CartCheckoutComponent, TitleContentsComponent],
  templateUrl: './cart.component.html',
  styles: ``
})
export class CartComponent {
}
