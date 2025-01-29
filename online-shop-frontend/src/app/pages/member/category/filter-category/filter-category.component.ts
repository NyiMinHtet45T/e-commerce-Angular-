import { Component, computed, effect, input, signal } from '@angular/core';
import { ProductCardComponent } from "../../product/product-card/product-card.component";
import { PagerComponent, PaginationComponent } from "../../../../widgets/pagination/pagination.component";
import { LoginUser, LoginUserState } from '../../../../service/security/login-user.state';
import { CategoryService } from '../../../../service/api/category.service';
import { ProductService } from '../../../../service/api/product.service';
import { MessageService } from '../../../../service/subject/message.service';
import { PageInfo } from '../../../../service/commons';
import { CategoryComponent } from "../category.component";
import { AdminProductService } from '../../../../service/api/admin/admin-product.service';

@Component({
  selector: 'app-filter-category',
  imports: [ProductCardComponent, PaginationComponent, CategoryComponent],
  templateUrl: './filter-category.component.html',
  styles: ``
})
export class FilterCategoryComponent implements PagerComponent{
  pageInfo = signal<PageInfo | undefined>(undefined);
  contents = computed(() => this.pageInfo()?.contents || []);

  page = signal<number>(0);
  size = signal<number>(8);

  user = signal<LoginUser | undefined>(undefined);
  userId = computed(() => this.user()?.id);

  constructor(private categoryService:CategoryService,
    private productService:ProductService,
    private loginUser: LoginUserState,
    private messageService: MessageService,
    private adProductService:AdminProductService) {
    this.user.set(loginUser.user());
    effect(() => {
      if(this.name()) {
        this.getProductByCategoryName();
      }
    })

  }

  name = input<string>();

  getProductByCategoryName() {
    this.productService.getProductByCategoryName(this.name()!,this.userId(),this.page(), this.size()).subscribe(result => {
      this.pageInfo.set(result);
    })
  }

  handleDeleteProduct(productId:number) {
    this.adProductService.deleteProduct(productId,this.loginUser.user()?.id!).subscribe(result => {
      this.messageService.setMessage(result.message);
      this.getProductByCategoryName();
    })
  }

  handleAddToFavourite(productId : number) {
    if(this.userId()) {
      this.productService.addToFavourite(productId, this.userId()).subscribe(result => {
        this.messageService.setMessage(result.message);
       
      })
    }else {
      this.messageService.setMessage("You need To Login First!")
    }
  }

  linkChange(page: number): void {
    this.page.set(page);
   
  }

  sizeChange(size: number): void {
    this.size.set(size);
   
  }
}
