import { Injectable } from "@angular/core";
import { enviroment } from "../../../enviroments/enviroment";
import { HttpClient } from "@angular/common/http";
import { PageInfo } from "../commons";

const PRODUCT_URL = `${enviroment.baseURL}/api/product`

@Injectable({providedIn : 'root'})
export class ProductService {

   constructor(private http:HttpClient) {}

   getAllProducts = (userId : number | undefined, page : number, size : number) => {
      if(userId == undefined) {
         return this.http.get<PageInfo>(`${PRODUCT_URL}/?page=${page}&size=${size}`);
      }
      return this.http.get<PageInfo>(`${PRODUCT_URL}/?userId=${userId}&page=${page}&size=${size}`);
   }

   searchProductByName = (name: string, userId : number | undefined, page : number, size : number) => {
      if(userId == undefined) {
         return this.http.get<PageInfo>(`${PRODUCT_URL}/search?name=${name}&page=${page}&size=${size}`);
      }
      return this.http.get<PageInfo>(`${PRODUCT_URL}/search?name=${name}&userId=${userId}&page=${page}&size=${size}`);
   }

   getProductByCategoryName = (name: string, userId : number | undefined, page : number, size : number) => {
      if(userId == undefined) {
         return this.http.get<PageInfo>(`${PRODUCT_URL}/category/${name}?page=${page}&size=${size}`);
      }
      return this.http.get<PageInfo>(`${PRODUCT_URL}/category/${name}?userId=2&page=${page}&size=${size}`);
   }

   addToFavourite = (productId:number, userId:number | undefined) => {
      return this.http.get<any>(`${PRODUCT_URL}/add_to_favorite/productId/${productId}/userId/${userId}`);
   }

   getProductById = (productId: number) => {
      return this.http.get<any>(`${PRODUCT_URL}/productId/${productId}`)
   }

   getAllFavourites = (userId:number | undefined) => {
      return this.http.get<any[]>(`${PRODUCT_URL}/userId/${userId}`)
   }

}