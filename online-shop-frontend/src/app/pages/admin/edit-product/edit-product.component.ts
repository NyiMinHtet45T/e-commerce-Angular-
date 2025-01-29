import { Component , computed, effect, input, NgModule, signal} from '@angular/core';
import { FormContentsComponent } from "../../../widgets/form-contents/form-contents.component";
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { AdminProductService } from '../../../service/api/admin/admin-product.service';
import { MessageService } from '../../../service/subject/message.service';
import { CategoryService } from '../../../service/api/category.service';
import { AdminCategoryService } from '../../../service/api/admin/admin-category.service';
import { ProductService } from '../../../service/api/product.service';

@Component({
  selector: 'app-edit-product',
  standalone: true,
  imports: [FormContentsComponent, ReactiveFormsModule, CommonModule, MatIconModule],
  templateUrl: './edit-product.component.html',
  styles: ``
})
export class EditProductComponent {

  preview : string[] = [];
  form:FormGroup;

  constructor(private builder:FormBuilder,
    private adproductService:AdminProductService,
    private messageService:MessageService,
    private categoryService:CategoryService,
    private adCategoryService:AdminCategoryService,
    private productService: ProductService) {
    this.form = builder.group({
      id: [''],
      name: ['', Validators.required],
      price: ['', Validators.required],
      description: [''],
      img: builder.array([], Validators.required),
      available: [true],
      categoryName: ['']
    })
    this.cateogryForm = builder.group({
      id: [''],
      name: ['', Validators.required]
    })

    this.getCategories();

    effect(() => {
      if(this.productId()) {
        this.getProduct();
      }
    })
  }

  product = signal<any>(undefined);
  image = computed(() => this.product()?.img);

  getProduct() {
    this.productService.getProductById(this.productId()!).subscribe(result => {
      this.product.set(result);
      this.handleUpdate();
      this.image().forEach((image:string) => this.preview.push(image));
    })
  }

  handleUpdate() {

    this.image().forEach((image:string) => this.images.push(this.builder.control(image)));

    this.form.patchValue({
      id: this.product().id,
      name: this.product().name,
      price: this.product().price,
      description: this.product().description,
      available: this.product().available,
      categoryName: this.product().categoryName,
    })
    
  }

  get images(): FormArray {
    return this.form.get('img') as FormArray
  }

  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;

    if(input.files) {
      Array.from(input.files).forEach((file: File) => {
        const allowedTypes = ['image/jpeg', 'image/png', 'image/gif'];
        if(!allowedTypes.includes(file.type)) {
          alert('only JPEG, PNG, and GIF files are allowed.');
          return;
        }

        if(file.size > 5 * 1024 * 1024) {
          alert('File size exceeds 5MB');
          return;
        }

        this.images.push(this.builder.control(file.name));
        const reader = new FileReader();
        reader.onload = () => {
          this.preview.push(reader.result as string);
        };
        reader.readAsDataURL(file);
      })
    }
  }

  productId = input<number>();

  handleRemove(index :number) {
    this.images.removeAt(index);
    this.preview.splice(index, 1);

    console.log(this.images.length)
  }

  handleSubmit() {
    if(this.form.valid) {
      if(this.productId() != undefined) {
        this.adproductService.updateProduct(this.form.value).subscribe(result => {
          this.messageService.setMessage("Successfully update")
        })
      }else {
      this.adproductService.createProduct(this.form.value).subscribe(result => {
        this.messageService.setMessage("Successfully create");
      })
      }
    }
  }

  categories = signal<any[]>([]);

  cateogryForm: FormGroup;

  handleSubmitCategory() {
    if(this.cateogryForm.valid) {
      this.adCategoryService.createCategory(this.cateogryForm.value).subscribe({
        next : result => {
          this.messageService.setMessage(result.message);
          this.getCategories();
        },
        error: error => {
          console.log(error)
        }
      })
    }
  }

  getCategories() {
    this.categoryService.getAllCategories().subscribe({
      next: (result) => {
        this.categories.set(result);
      },
      error: (error) => {
        console.log(error)
      }
    });
  }

  handleDeleteCategory(categoryId:number) {
    this.adCategoryService.deleteCategory(categoryId).subscribe({
      next : (result) => {
        this.messageService.setMessage(result.message);
        this.getCategories();
      },
      error: (error) => {
        console.log(error)
      }
    })
  }
}
