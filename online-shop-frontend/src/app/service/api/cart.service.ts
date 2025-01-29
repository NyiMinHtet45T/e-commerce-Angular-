import { Injectable } from "@angular/core";
import { enviroment } from "../../../enviroments/enviroment";
import { HttpClient } from "@angular/common/http";
import { BehaviorSubject } from "rxjs";
import { MessageService } from "../subject/message.service";

const CART_URL = `${enviroment.baseURL}/api/cart`;

@Injectable({providedIn : 'root'})
export class CartService {

   private cartTotalItemAndPriceSubject = new BehaviorSubject<any | undefined>(undefined);
   cartTotalItemAndPrice$ = this.cartTotalItemAndPriceSubject.asObservable();

   constructor(private http:HttpClient, private messageService:MessageService) {}

   addCartItemToCart = (cartObj : any) => {
      return this.http.post<any>(`${CART_URL}/`, cartObj).subscribe(result => {
         this.messageService.setMessage(result.message);
         this.cartTotalAndPrice(cartObj.userId)
      });
   }

   addCartQuantity = (cartItemId: number, totalQuantity: number) => {
      return this.http.get<any>(`${CART_URL}/itemId/${cartItemId}/total_quantity/${totalQuantity}`);
   }

   cartTotalAndPrice = (userId: number | undefined) => {
      return this.http.get<any>(`${CART_URL}/total_and_price/${userId}`).subscribe(result => {
         this.cartTotalItemAndPriceSubject.next(result);
      });
   }

   removeCartItemFromCart = (cartItemId: number, userId: number | undefined) => {
      return this.http.delete<any>(`${CART_URL}/remove/itemId/${cartItemId}/userId/${userId}`);
   }

   clearCart = (userId: number) => {
      return this.http.delete<any>(`${CART_URL}/clear/userId/${userId}`);
   }

   getCartByUserId = (userId: number | undefined) => {
      return this.http.get<any>(`${CART_URL}/get_cart_userId/${userId}`);
   }

   getCartById = (cartId: number) => {
      return this.http.get<any>(`${CART_URL}/get_cart/${cartId}`);
   }

   updateItemAndCart = (itemAndCart: any) => {
      this.cartTotalItemAndPriceSubject.next(itemAndCart);
   }

}