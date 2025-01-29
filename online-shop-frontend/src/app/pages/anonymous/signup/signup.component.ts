import { CommonModule } from '@angular/common';
import { Component, signal, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { FormContentsComponent } from "../../../widgets/form-contents/form-contents.component";
import { SecurityService } from '../../../service/api/security.service';
import { MessageService } from '../../../service/subject/message.service';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [RouterLink, ReactiveFormsModule, CommonModule, FormContentsComponent],
  templateUrl: './signup.component.html',
  styles: ``
})
export class SignupComponent {

  form:FormGroup;

  constructor(private router: Router,
    private builder:FormBuilder,
    private securityService: SecurityService,
    private messageService: MessageService) {
    this.form = builder.group({
      name : ['', Validators.required],
      email : ['', [Validators.required, Validators.email]],
      password : ['', [Validators.required, Validators.minLength(4)]],
      phoneNumber: ['', [Validators.required, Validators.minLength(7)]]
    })
  }

  handleSubmit() {
    if(this.form.valid) {
      this.securityService.signUp(this.form.value).subscribe(result => {
        this.messageService.setMessage(result.message)
        this.router.navigate(['/anonymous/signin']);
      })
    }
  }
}
