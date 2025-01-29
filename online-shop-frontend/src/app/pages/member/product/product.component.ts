import { Component, computed, signal } from '@angular/core';
import { PagerComponent, PaginationComponent } from "../../../widgets/pagination/pagination.component";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from '@angular/material/button'; 
import { CommonModule } from '@angular/common';
import { ProductCardComponent } from "./product-card/product-card.component";
import { ProductService } from '../../../service/api/product.service';
import { PageInfo } from '../../../service/commons';
import { LoginUser, LoginUserState } from '../../../service/security/login-user.state';
import { Router } from '@angular/router';
import { MessageService } from '../../../service/subject/message.service';
import { CartService } from '../../../service/api/cart.service';
import { AdminProductService } from '../../../service/api/admin/admin-product.service';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [MatIconModule, MatButtonModule, CommonModule, ProductCardComponent, PaginationComponent],
  templateUrl: './product.component.html',
  styles: ``,
})
export class ProductComponent implements PagerComponent{

  pageInfo = signal<PageInfo | undefined>(undefined);
  contents = computed(() => this.pageInfo()?.contents || []);

  page = signal<number>(0);
  size = signal<number>(8);

  user = signal<LoginUser | undefined>(undefined);
  userId = computed(() => this.user()?.id);

  constructor(private router:Router,
    private productService:ProductService,
    private loginUser: LoginUserState,
    private messageService: MessageService,
    private cartService: CartService,
    private adProductService:AdminProductService) {

    this.user.set(loginUser.user());
    this.getAllProduct();

  }

  linkChange(page: number): void {
    this.page.set(page);
    this.getAllProduct();
  }

  sizeChange(size: number): void {
    this.size.set(size);
    this.getAllProduct();
  }

  handleDeleteProduct(productId:number) {
    this.adProductService.deleteProduct(productId,this.loginUser.user()?.id!).subscribe(result => {
      this.messageService.setMessage(result.message);
      this.getAllProduct();
    })
  }

  getAllProduct() {
    this.productService.getAllProducts(this.userId(), this.page(), this.size()).subscribe(result => {
      this.pageInfo.set(result);
    })
  }

  handleAddToFavourite(productId : number) {
    if(this.userId()) {
      this.productService.addToFavourite(productId, this.userId()).subscribe(result => {
        this.messageService.setMessage(result.message);
        this.getAllProduct(); 
      })
    }else {
      this.messageService.setMessage("You need To Login First!")
    }
  }
}
