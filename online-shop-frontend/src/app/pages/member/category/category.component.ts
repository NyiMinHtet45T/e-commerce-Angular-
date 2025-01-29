import { Component, input, signal } from '@angular/core';
import { ProductComponent } from "../product/product.component";
import { RouterLink } from '@angular/router';
import { CategoryService } from '../../../service/api/category.service';
import { ProductCardComponent } from "../product/product-card/product-card.component";
import { PaginationComponent } from "../../../widgets/pagination/pagination.component";

@Component({
  selector: 'app-category',
  standalone: true,
  imports: [ RouterLink],
  templateUrl: './category.component.html',
  styles: ``
})
export class CategoryComponent{

  categories = signal<any[]>([]);

  currentCategory = input<string>();

  constructor(private categoryService:CategoryService) {

    this.getCategories();

  }

  getCategories = () => {
    this.categoryService.getAllCategories().subscribe(result => {
      this.categories.set(result);
    })
  }
}
