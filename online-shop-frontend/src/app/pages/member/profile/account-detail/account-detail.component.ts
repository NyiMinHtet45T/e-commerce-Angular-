import { Component, signal } from '@angular/core';
import { UserService } from '../../../../service/api/user.service';
import { LoginUserState } from '../../../../service/security/login-user.state';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SecurityService } from '../../../../service/api/security.service';
import { MessageService } from '../../../../service/subject/message.service';

@Component({
  selector: 'app-account-detail',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './account-detail.component.html',
  styles: ``
})
export class AccountDetailComponent {

  user = signal<any>(undefined);

  profielDetail: any = []

  passwordForm:FormGroup;

  constructor(private userService:UserService,private securityService:SecurityService,
    private messageService:MessageService, private loginUser:LoginUserState, private builder:FormBuilder) {
    this.getUser();
    this.passwordForm = builder.group({
      username: [loginUser.user()?.name],
      oldPassword: ['', [Validators.required, Validators.minLength(4)]],
      newPassword: ['', [Validators.required, Validators.minLength(4)]]
    })
  }

  getUser() {
    this.userService.getUserById(this.loginUser.user()?.id).subscribe(result => {
      this.user.set(result)
      this.profielDetail = [
        {title : 'name' , value : this.user()?.name},
        {title : 'email' , value : this.user()?.email},
        {title : 'phone' , value : this.user()?.phoneNumber},
        {title : 'role' , value : this.loginUser.role()}]
    })
  }

  isChange = signal<boolean>(false);

  handleChangePassword() {
    this.isChange.set(!this.isChange());
    if(this.passwordForm.valid) {
      this.securityService.changePassword(this.passwordForm.value).subscribe(result => {
        this.messageService.setMessage("Successfully Change Password!")
        this.passwordForm.patchValue({
          oldPassword: '',
          newPassword:'',
      })

      })  
    }
  }

}
