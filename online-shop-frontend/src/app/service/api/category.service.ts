import { Injectable } from "@angular/core";
import { enviroment } from "../../../enviroments/enviroment";
import { HttpClient } from "@angular/common/http";

const CATEGORY_URL = `${enviroment.baseURL}/api/category`;

@Injectable({providedIn : 'root'})
export class CategoryService {

   constructor(private http:HttpClient){}

   getAllCategories = () => {
      return this.http.get<any[]>(`${CATEGORY_URL}/`)
   }
}