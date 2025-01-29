import { CommonModule } from '@angular/common';
import { Component, signal } from '@angular/core';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { Router, RouterOutlet } from '@angular/router';
import { LoginUserState } from '../../../service/security/login-user.state';
import { UserService } from '../../../service/api/user.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [RouterOutlet, MatIconModule, CommonModule],
  templateUrl: './profile.component.html',
  styles: ``
})
export class ProfileComponent {

  sideNav = signal<boolean>(false);

  navTab = signal([
    {title : 'Orders', icon : 'inventory_2'},
    {title : 'Detail', icon : 'manage_accounts'},
    {title : 'Logout', icon : 'logout'},
  ])
  
  user = signal<any>(undefined);

  constructor(private route:Router, private loginUser:LoginUserState, private userService:UserService) {
    this.getUser()
    const memberTab = [
      {title : 'Favorite', icon : 'favorite'},
      {title : 'Address', icon : 'home_pin'}
    ]

    const adminTab = [
      {title : 'Create', icon : 'draw'},
    ]

    if(loginUser.user()?.userRole == "ADMIN") {
      this.navTab.set([...adminTab ,...this.navTab()])
    }else {
      this.navTab.set([...memberTab ,...this.navTab()])
    }
  }

  getUser() {
    this.userService.getUserById(this.loginUser.user()?.id).subscribe(result => {
      this.user.set(result)
    })
  }

  handleRoute(tabName: string) {
    if(tabName === 'Logout') {
      this.loginUser.signOut();
      this.route.navigate(['']);
    }else {
      this.route.navigate(['/members', 'profile' , tabName.toLowerCase()])
    }
  }

  handleNavClose() {
    this.sideNav.set(!this.sideNav());
  }

}
