import { Component } from '@angular/core';
import { SignupComponent } from "./signup/signup.component";
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-anonymous',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './anonymous.component.html',
  styles: ``
})
export class AnonymousComponent {
  
}
