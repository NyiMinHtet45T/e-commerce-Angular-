import { CommonModule } from '@angular/common';
import { Component, computed, EventEmitter, input, Output, signal } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Router, RouterLink } from '@angular/router';
import { ProductService } from '../../../../service/api/product.service';
import { LoginUser, LoginUserState } from '../../../../service/security/login-user.state';
import { MessageService } from '../../../../service/subject/message.service';
import { CartService } from '../../../../service/api/cart.service';
import { AdminProductService } from '../../../../service/api/admin/admin-product.service';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [MatIconModule, CommonModule],
  templateUrl: './product-card.component.html',
  styles: ``
})
export class ProductCardComponent{

  user = signal<LoginUser | undefined>(undefined);
  userId = computed(() => this.user()?.id);

  contents = input.required<any[]>();

  constructor(private router:Router,
    private productService:ProductService,
    private adminService:AdminProductService,
    private loginUser: LoginUserState,
    private messageService: MessageService,
    private cartService: CartService) {
    this.user.set(loginUser.user());
  }

  goDetail(id: number) {
    this.router.navigate(['product/detail/', id])
  }

  @Output()
  onProductChange = new EventEmitter<number>;

  handleAddToFavourite(productId:number) {
    this.onProductChange.emit(productId);
  }

  handleAddToCart = (productObj:any) => {
    if(this.userId()) {
      const cartObj = {"quantity": 1,"totalPrice": productObj.price,"productId": productObj.id,"userId": this.userId()};
      this.cartService.addCartItemToCart(cartObj);
    }else {
      this.messageService.setMessage("You need to login First!");
    }
  }

  @Output()
  onProductIdChange = new EventEmitter<number>();

  handleDelete(productId:number) {
    this.onProductIdChange.emit(productId);
  }

  handleUpdate(productId:number) {
    this.router.navigate(['admin/', productId]);
  }

  isAdmin() {
    if(this.loginUser.user()?.userRole == 'ADMIN') {
      return true;
    }
    return false;
  }
}
