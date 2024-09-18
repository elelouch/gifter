import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, RouterOutlet, MatToolbarModule, MatButtonModule, MatIconModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  mainUsernamePath = "";
  friendsRoute = "";

  constructor(private authService: AuthService){
    this.authService.user$.subscribe((user) => {
      this.mainUsernamePath = `/app/user/${user.username}`
    })
    this.friendsRoute = "/app/friends/search"
  }

  logout() {
    this.authService.logout()
  }

}
