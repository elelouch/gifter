import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterModule, RouterOutlet } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [ RouterOutlet, RouterModule, RouterLink, RouterLinkActive,MatToolbarModule, MatButtonModule, MatIconModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  username = "username";

  constructor(private authService: AuthService){
    this.authService.user$.subscribe((user) => {
      this.username = user.username;
    })
  }

  logout() {
    this.authService.logout()
  }

}
