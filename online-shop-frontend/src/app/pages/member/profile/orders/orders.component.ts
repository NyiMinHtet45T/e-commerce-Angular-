import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, computed, signal } from '@angular/core';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { OrderCardComponent } from "./order-card/order-card.component";
import { TitleContentsComponent } from "../../../../widgets/title-contents/title-contents.component";
import { OrderService } from '../../../../service/api/order.service';
import { LoginUser, LoginUserState } from '../../../../service/security/login-user.state';
import { MessageService } from '../../../../service/subject/message.service';


@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [MatIconModule, CommonModule, OrderCardComponent, TitleContentsComponent],
  templateUrl: './orders.component.html',
  styles: ``
})
export class OrdersComponent{

  order = signal<any[]>([]);

  constructor(private orderService:OrderService, private loginUser:LoginUserState, private messageService:MessageService) {
    this.getOrder();
  }

  getOrder() {
    if(this.loginUser.user()?.userRole == 'ADMIN') {
      this.orderService.getAllOrder().subscribe(result => {
        this.order.set(result);
      })
    }else {
      this.orderService.getOrderByUserId(this.loginUser.user()?.id).subscribe(result => {
        this.order.set(result);
      })
    }
  }

  handleDeleteOrder(orderId:number) {
    this.orderService.deleteOrder(orderId, this.loginUser.user()?.id!).subscribe(result => {
      this.messageService.setMessage(result.message);
      this.getOrder();
    })
  }

  handleCancelOrderItem(orderObj:any) {
    this.orderService.cancelOrderItem(orderObj.orderItemId, orderObj.orderId).subscribe(result => {
      this.messageService.setMessage(result.message);
      this.getOrder();
    })
  }

  handleStateChange(stateObj: any) {
    this.orderService.updateOrderItemStatus(stateObj.orderItemId, stateObj.state).subscribe(result => {
      this.getOrder();
    })
  }

  isAdmin() {
    if(this.loginUser.user()?.userRole == 'ADMIN') {
      return true;
    }
    return false;
  }

}
