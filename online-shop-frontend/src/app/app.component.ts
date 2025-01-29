import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {MatIconModule} from "@angular/material/icon"
import { MatButtonModule } from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavbarComponent } from "./navbar/navbar.component";
import { GlobalMessageBoxComponent } from "./widgets/global-message-box/global-message-box.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MatIconModule, MatButtonModule, NavbarComponent, GlobalMessageBoxComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'e-commerce-website';
}
