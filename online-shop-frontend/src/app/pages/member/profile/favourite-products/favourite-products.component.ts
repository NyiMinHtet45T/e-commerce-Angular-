import { Component, signal } from '@angular/core';
import { ProductComponent } from "../../product/product.component";
import { TitleContentsComponent } from "../../../../widgets/title-contents/title-contents.component";
import { ProductService } from '../../../../service/api/product.service';
import { LoginUserState } from '../../../../service/security/login-user.state';
import { ProductCardComponent } from "../../product/product-card/product-card.component";
import { MessageService } from '../../../../service/subject/message.service';
import { AdminProductService } from '../../../../service/api/admin/admin-product.service';

@Component({
  selector: 'app-favourite-products',
  standalone: true,
  imports: [ProductComponent, TitleContentsComponent, ProductCardComponent],
  templateUrl: './favourite-products.component.html',
  styles: ``
})
export class FavouriteProductsComponent {

  favourite = signal<any[]>([]);

  constructor(private productService:ProductService,private adProductService:AdminProductService, private loginUser:LoginUserState, private messageService:MessageService) {
    this.getFavourite();
  }

  getFavourite() {
    this.productService.getAllFavourites(this.loginUser.user()?.id).subscribe(result => {
      this.favourite.set(result);
    })
  }

  handleAddToFavourite(productId:number) {
    this.productService.addToFavourite(productId, this.loginUser.user()?.id).subscribe(result => {
          this.messageService.setMessage(result.message);
          this.getFavourite() 
        })
  }

  handleDeleteProduct(productId:number) {
    this.adProductService.deleteProduct(productId, this.loginUser.user()?.id!).subscribe(result => {
      this.messageService.setMessage(result.message);
      this.getFavourite();
    })
  }

}
