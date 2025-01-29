import { Injectable } from "@angular/core";
import { enviroment } from "../../../enviroments/enviroment";
import { HttpClient } from "@angular/common/http";

const ORDER_URL = `${enviroment.baseURL}/api/order`;

@Injectable({providedIn: 'root'})
export class OrderService {

   constructor(private http:HttpClient) {

   }

   createOrder = (orderObj: any) => {
      return this.http.post<any>(`${ORDER_URL}/`, orderObj);
   }

   updateOrderItemStatus = (orderItemId: number, orderStatus: string) => {
      return this.http.get<any>(`${ORDER_URL}/itemId/${orderItemId}/state/${orderStatus}`)
   }

   cancelOrderItem = (orderItemId: number, orderId: number) => {
      return this.http.delete<any>(`${ORDER_URL}/itemId/${orderItemId}/orderId/${orderId}`)
   }

   deleteOrder = (orderId: number, userId: number) => {
      return this.http.delete<any>(`${ORDER_URL}/orderId/${orderId}/userId/${userId}`)
   }

   getOrderByUserId = (userId: number | undefined) => {
      return this.http.get<any[]>(`${ORDER_URL}/userId/${userId}`)
   }

   getAllOrder = () => {
      return this.http.get<any[]>(`${ORDER_URL}/`)
   }

}