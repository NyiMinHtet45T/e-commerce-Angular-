import { Component } from '@angular/core';
import { CategoryComponent } from "../category/category.component";
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';
import { ProductComponent } from "../product/product.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CategoryComponent, MatIconModule, RouterLink, ProductComponent],
  templateUrl: './home.component.html',
  styles: ``
})
export class HomeComponent {

}
