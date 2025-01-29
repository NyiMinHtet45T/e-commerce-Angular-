import { Injectable } from "@angular/core";
import { enviroment } from "../../../enviroments/enviroment";
import { HttpClient } from "@angular/common/http";
import { LoginUser } from "../security/login-user.state";

const SECURITY_URL = `${enviroment.baseURL}/security`

@Injectable({providedIn : 'root'})
export class SecurityService {

   constructor(private http:HttpClient) {}

   signUp = (form:any) => {
      return this.http.post<any>(`${SECURITY_URL}/register`, form);
   }

   signIn = (form:any) => {
      return this.http.post<LoginUser>(`${SECURITY_URL}/login`, form);
   }

   refresh = (form: any) => {
      return this.http.post<LoginUser>(`${SECURITY_URL}/refresh`, form);
   }

   changePassword = (form: any) => {
      return this.http.post<LoginUser>(`${SECURITY_URL}/change_password`, form);
   }

}
