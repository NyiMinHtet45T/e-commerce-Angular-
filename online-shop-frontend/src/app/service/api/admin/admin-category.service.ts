import { Injectable } from "@angular/core";
import { enviroment } from "../../../../enviroments/enviroment";
import { HttpClient } from "@angular/common/http";

const ADMIN_CATEGORY_URL = `${enviroment.baseURL}/api/admin/category`;

@Injectable({providedIn: 'root'})
export class AdminCategoryService {

   constructor(private http:HttpClient) {}

   createCategory = (categoryObj : any) => {
      return this.http.post<any>(`${ADMIN_CATEGORY_URL}/`, categoryObj);
   }

   updateCategory = (categoryObj : any) => {
      return this.http.put<any>(`${ADMIN_CATEGORY_URL}/`, categoryObj);
   }

   deleteCategory = (categoryId : number) => {
      return this.http.delete<any>(`${ADMIN_CATEGORY_URL}/${categoryId}`);
   }
}