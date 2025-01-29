import { Component, computed, EventEmitter, input, Output, signal } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { OrderService } from '../../../../../service/api/order.service';
import { LoginUser, LoginUserState } from '../../../../../service/security/login-user.state';

@Component({
  selector: 'app-order-card',
  standalone: true,
  imports: [MatIconModule],
  templateUrl: './order-card.component.html',
  styles: ``
})
export class OrderCardComponent {

  showContent = signal<boolean>(false);
  content = signal<number | undefined>(undefined);

  isAdmin = input.required<boolean>();

  handleShowContent(index:number) {
    this.showContent.set(!this.showContent());
    this.content.set(index);
  }

  order = input.required<any[]>();

  constructor(private loginUser:LoginUserState) {

  }

  @Output()
  onOrderDeleteChange = new EventEmitter<number>();

  @Output()
  onItemDeleteChange = new EventEmitter<any>();

  @Output()
  onItemStateChange = new EventEmitter<any>();

  handleDeleteOrder(orderId:number) {
    this.onOrderDeleteChange.emit(orderId);
  }

  handleCancelOrderItem(orderId:number ,orderItemId:number) {
    this.onItemDeleteChange.emit({orderId: orderId, orderItemId: orderItemId});
  }

  handleUpdateState(state: string, orderItemId:number) {
      this.onItemStateChange.emit({state: state, orderItemId: orderItemId});
  }

}
