import { Injectable } from "@angular/core";
import { enviroment } from "../../../enviroments/enviroment";
import { HttpClient } from "@angular/common/http";
import { MessageService } from "../subject/message.service";

const ADDRESS_URL = `${enviroment.baseURL}/api/address`;

@Injectable({providedIn : 'root'})
export class AddressService {

   constructor(private http:HttpClient) {}

   createAddress = (addressObj:any) => {
      return this.http.post<any>(`${ADDRESS_URL}/`, addressObj);
   }

   updateAddress = (addressObj:any) => {
      return this.http.put<any>(`${ADDRESS_URL}/`, addressObj);
   }

   deleteAddress = (addressId:number) => {
      return this.http.delete<any>(`${ADDRESS_URL}/${addressId}`);
   }

   getAddressByUserId = (userId:number | undefined) => {
      return this.http.get<any[]>(`${ADDRESS_URL}/userId/${userId}`);
   }

}