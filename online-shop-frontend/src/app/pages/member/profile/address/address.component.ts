import { Component, computed, effect, input, OnChanges, OnInit, signal, SimpleChanges } from '@angular/core';
import { TitleContentsComponent } from "../../../../widgets/title-contents/title-contents.component";
import { AddressService } from '../../../../service/api/address.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginUser, LoginUserState } from '../../../../service/security/login-user.state';
import { CommonModule } from '@angular/common';
import { FormContentsComponent } from "../../../../widgets/form-contents/form-contents.component";
import { MatIconModule } from '@angular/material/icon';
import { MessageService } from '../../../../service/subject/message.service';
import { OrderService } from '../../../../service/api/order.service';
import { Router } from '@angular/router';
import { CartService } from '../../../../service/api/cart.service';

@Component({
  selector: 'app-address',
  standalone: true,
  imports: [TitleContentsComponent, ReactiveFormsModule, CommonModule, FormContentsComponent, MatIconModule],
  templateUrl: './address.component.html',
  styles: ``
})
export class AddressComponent{

  addressForm: FormGroup;
  user = signal<LoginUser | undefined>(undefined);
  userId = computed(() => this.user()?.id);

  addressList = signal<any[]>([]);

  constructor(private addressService: AddressService,
     private builder: FormBuilder,
      private loginUser:LoginUserState,
    private messageService:MessageService,
    private orderService:OrderService,
    private router:Router,
    private cartService: CartService) {
    this.addressForm = builder.group({
      id: [''],
      street: ['', Validators.required],
      city: ['', Validators.required],
      houseNo: ['', Validators.required],
      userId : ['']
    })
    this.user.set(loginUser.user());
    this.getAddressByUserId();

    effect(() => {
      if(!this.showMessage()) {
        this.text.set('create');
      }
    }, {allowSignalWrites: true})

    effect(() => {
      if(this.isorder()) {
        this.text.set('order');
        this.showMessage.set(true);
      }
    })

    effect(() => {
      if(this.text()) {
        this.handleTitle();
      }
    })
  }

  handleTitle() {
    return this.text() === 'order' ? 'Select Address' :  'Address';
  }

  getAddressByUserId() {
    this.addressService.getAddressByUserId(this.userId()).subscribe(result => {
      this.addressList.set(result);
    });
  }

  isorder = input<string>();

  showModel = signal<boolean>(false);

  handleClose = () => {
    this.showModel.set(false)
    this.addressForm.patchValue({
      id: '',
      street: '',
      city: '',
      houseNo: ''
    })
  }

  handleOpen = () => {
    this.showModel.set(true);
  }

  handleCreateAddress() {
    this.addressService.createAddress(this.addressForm.value).subscribe(result => {
      this.messageService.setMessage(result.message);
      this.getAddressByUserId();
      this.handleClose();
    });
  }

  showMessage = signal<boolean>(false);
  text = signal<string>('create');

  actionButton(text:string) {
    if(text == 'update') {
      this.text.set('update');
    }else {
      this.text.set('delete');
    }
    this.showMessage.set(!this.showMessage())
  }

  gateAddressSender(address: any) {
    if(this.text() != '') {
      if(this.text() === 'update' || this.text() === 'order') {
        this.addressForm.patchValue({
          id: address.id,
          street: address.street,
          city: address.city,
          houseNo: address.houseNo
        })
        this.handleOpen();
      }else if(this.text() === 'delete'){
        this.handleDeleteAddress(address.id);
      }
    }
  }

  handleUpdateAddress() {
    this.addressService.updateAddress(this.addressForm.value).subscribe(result => {
      this.messageService.setMessage(result.message);
      this.getAddressByUserId();
      this.handleClose();
    });
  }

  handleDeleteAddress(addressId:number) {
    this.addressService.deleteAddress(addressId).subscribe(result => {
      this.messageService.setMessage(result.message);
      this.getAddressByUserId();
    })
  }

  handleOrder() {
    this.orderService.createOrder({userId : this.userId(), addressId: this.addressForm.get('id')?.value}).subscribe(result => {
      this.messageService.setMessage(result.message);
      this.router.navigate(['members/profile/orders']);
      this.cartService.cartTotalAndPrice(this.user()?.id)
    })
  }

  handleAddress() {
    if(this.addressForm.valid) {
      this.addressForm.patchValue({userId : this.user()?.id})
      if(this.text() === 'update') {
        this.handleUpdateAddress();
      } else if(this.text() === 'order') {
       this.handleOrder();
      } else {
        this.handleCreateAddress();
      }  
    }
  }
  

}
