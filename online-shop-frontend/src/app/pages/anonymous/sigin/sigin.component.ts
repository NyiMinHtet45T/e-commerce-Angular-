import { Component, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormContentsComponent } from "../../../widgets/form-contents/form-contents.component";
import { SecurityService } from '../../../service/api/security.service';
import { LoginUserState } from '../../../service/security/login-user.state';
import { MessageService } from '../../../service/subject/message.service';

@Component({
  selector: 'app-sigin',
  standalone: true,
  imports: [RouterLink, CommonModule, ReactiveFormsModule, FormContentsComponent],
  templateUrl: './sigin.component.html',
  styles: ``
})
export class SiginComponent {

  form:FormGroup;

  constructor(builder: FormBuilder,
    private securityService: SecurityService,
    private loginUser : LoginUserState,
    private router:Router,
    private messageService: MessageService) {
    this.form = builder.group({
      name : ['', Validators.required],
      password : ['', [Validators.required, Validators.minLength(4)]]
    })
  }

  handleSubmit() {
    if(this.form.valid) {
      this.securityService.signIn(this.form.value).subscribe(result => {
        this.loginUser.setUser(result);
        this.messageService.setMessage("Successfully Login!")
        this.router.navigate(['home'])
      });
    }
  }

}
