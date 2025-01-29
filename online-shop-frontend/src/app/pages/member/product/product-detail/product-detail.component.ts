import { Component, computed, effect, input, signal } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { CommentComponent } from "./comment/comment.component";
import { CommonModule } from '@angular/common';
import { TitleContentsComponent } from "../../../../widgets/title-contents/title-contents.component";
import { ProductService } from '../../../../service/api/product.service';
import { single } from 'rxjs';
import { CommentService } from '../../../../service/api/comment.service';
import { LoginUser, LoginUserState } from '../../../../service/security/login-user.state';
import { CartService } from '../../../../service/api/cart.service';
import { MessageService } from '../../../../service/subject/message.service';

@Component({
  selector: 'app-product-detail',
  standalone: true,
  imports: [MatIconModule, CommentComponent, CommonModule, TitleContentsComponent],
  templateUrl: './product-detail.component.html',
  styles: ''
})
export class ProductDetailComponent {

  slides = [1,1,1,1];
  currentIndex = signal(0);

  totalRating = signal<number>(0);

  user = signal<LoginUser | undefined>(undefined);
  userId = computed(() => this.user()?.id);

  constructor(private productService:ProductService,
     private cartService:CartService,
      private commentService:CommentService,
       private loginUser:LoginUserState,
      private messageService:MessageService) {

    this.user.set(loginUser.user());

    setInterval(() => {
      this.nextSlide();
    }, 5000);
    effect(() => {
      if(this.id()) {
        this.getProductDetail();
        
      }
    })
  }

  nextSlide() {
    this.currentIndex.update((index) => (index + 1) % this.slides.length);
  }

  prevSlide() {
    this.currentIndex.update((index) => (index - 1 + this.slides.length) % this.slides.length);
  }

  gotoSlide(index: number) {
    this.currentIndex.set(index);
  }

  product = signal<any | undefined>(undefined);

  id = input<number>();

  getProductDetail() {
    this.productService.getProductById(this.id()!).subscribe({
      next : result => { this.product.set(result)}
    })
  }

  handleAddToCart = () => {
    if(this.userId()) {
      const cartObj = {"quantity": 1,"totalPrice": this.product().price,"productId": this.id(),"userId": this.userId()};
      this.cartService.addCartItemToCart(cartObj);
    }else {
      this.messageService.setMessage("You need to login First!");
    }
    
  }
  
}
  


