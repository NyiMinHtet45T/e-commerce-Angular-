import { Injectable } from "@angular/core";
import { enviroment } from "../../../enviroments/enviroment";
import { HttpClient } from "@angular/common/http";
import { PageInfo } from "../commons";

const COMMENT_URL = `${enviroment.baseURL}/api/review`;

@Injectable({providedIn: 'root'})
export class CommentService {

   constructor(private http:HttpClient) {

   }

   createComment = (commentObj:any) => {
      return this.http.post<any>(`${COMMENT_URL}/`, commentObj);
   }

   getAllReview = (productId:number | undefined, size:number) => {
      return this.http.get<PageInfo>(`${COMMENT_URL}/?productId=${productId}&page=${0}&size=${size}`);
   }

   updateComment = (commentObj:any) => {
      return this.http.put<any>(`${COMMENT_URL}/`, commentObj);
   }

   getAvgRatingAndComment = (productId: number) => {
      return this.http.get<any>(`${COMMENT_URL}/${productId}`)
   }
}