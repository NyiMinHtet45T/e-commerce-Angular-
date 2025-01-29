import { Injectable } from "@angular/core";
import { enviroment } from "../../../../enviroments/enviroment";
import { HttpClient } from "@angular/common/http";

const ADMIN_PRODUCT_URL = `${enviroment.baseURL}/api/admin/product`;


@Injectable({providedIn: 'root'})
export class AdminProductService {

   constructor(private http:HttpClient) {}

   createProduct = (productObj: any) => {
      return this.http.post<any>(`${ADMIN_PRODUCT_URL}/`, productObj , { responseType: 'text' as 'json' });
   }

   updateProductAvailable = (productId: number) => {
      // return this.http.patch<string>(`${ADMIN_PRODUCT_URL}/update/available/${productId}`, { responseType: 'text' as 'json' });
   }

   updateProduct = (productObj: any) => {
      return this.http.put<any>(`${ADMIN_PRODUCT_URL}/`, productObj, { responseType: 'text' as 'json' });
   }

   deleteProduct = (productId: number, userId: number) => {
      return this.http.delete<any>(`${ADMIN_PRODUCT_URL}/${productId}/userId/${userId}`, { responseType: 'text' as 'json' });
   }

}