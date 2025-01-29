import { Injectable } from "@angular/core";
import { enviroment } from "../../../enviroments/enviroment";
import { HttpClient } from "@angular/common/http";

const USER_URL = `${enviroment.baseURL}/api/user`;

@Injectable({providedIn : 'root'})
export class UserService {

   constructor(private http:HttpClient){}

   getUserById = (userId: number | undefined) => {
      return this.http.get<any>(`${USER_URL}/${userId}`);
   }

}