import { computed, Injectable, signal } from "@angular/core";
import { MessageService } from "../subject/message.service";

const LOGINUSER = "com.home.e-commerce"

@Injectable({ providedIn : 'root'})
export class LoginUserState {

   user = signal<LoginUser | undefined>(undefined);
   role = computed(() => this.user()?.userRole);

   constructor(private messageService : MessageService) {
      const userStorage = localStorage.getItem(LOGINUSER);

      if(userStorage) {
         const loginUser:LoginUser = JSON.parse(userStorage);
         this.user.set(loginUser);
      }
   }

   setUser(user:LoginUser) {
      this.user.set(user);
      localStorage.setItem(LOGINUSER, JSON.stringify(user));
   }

   signOut() {
      this.user.set(undefined);
      localStorage.clear()
      this.messageService.setMessage("Successfully LogOut!")
   }

}

export interface LoginUser {
   id: number,
   name: string,
   userRole: 'ADMIN' | 'CUSTOMER',
   accessToken: string,
   refreshToken: string
}