import { Component, computed, effect, input, signal } from '@angular/core';
import { ProductComponent } from "../../pages/member/product/product.component";
import { PageInfo } from '../../service/commons';
import { PagerComponent, PaginationComponent } from '../../widgets/pagination/pagination.component';
import { ProductCardComponent } from "../../pages/member/product/product-card/product-card.component";
import { LoginUser, LoginUserState } from '../../service/security/login-user.state';
import { ProductService } from '../../service/api/product.service';
import { MessageService } from '../../service/subject/message.service';
import { AdminProductService } from '../../service/api/admin/admin-product.service';

@Component({
  selector: 'app-search-product',
  imports: [ ProductCardComponent, PaginationComponent],
  templateUrl: './search-product.component.html',
  styles: ''
})
export class SearchProductComponent implements PagerComponent{

  name = input<string>();

  pageInfo = signal<PageInfo | undefined>(undefined);
  contents = computed(() => this.pageInfo()?.contents || []);

  page = signal<number>(0);
  size = signal<number>(8);

  user = signal<LoginUser | undefined>(undefined);
  userId = computed(() => this.user()?.id);

  constructor(
    private productService:ProductService,
    private loginUser: LoginUserState,
    private messageService: MessageService,
    private adProductService:AdminProductService) {
      effect(() => {
        if(this.name()) {
          this.getSearchProduct();
        }
      })
  }

  linkChange(page: number): void {
    this.page.set(page);
    this.getSearchProduct();
  }

  sizeChange(size: number): void {
    this.size.set(size);
    this.getSearchProduct();
  }

  getSearchProduct() {
    this.productService.searchProductByName(this.name()!, this.userId(), this.page(), this.size()).subscribe(result => {
      this.pageInfo.set(result);
    })
  }

  handleDeleteProduct(productId:number) {
    this.adProductService.deleteProduct(productId, this.loginUser.user()?.id!).subscribe(result => {
      this.messageService.setMessage(result.message);
      this.getSearchProduct();
    })
  }

  handleAddToFavourite(productId : number) {
    if(this.userId()) {
      this.productService.addToFavourite(productId, this.userId()).subscribe(result => {
        this.messageService.setMessage(result.message);
        this.getSearchProduct(); 
      })
    }else {
      this.messageService.setMessage("You need To Login First!")
    }
  }
}
