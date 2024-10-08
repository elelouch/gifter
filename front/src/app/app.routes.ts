import { Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { UserComponent } from './user/user.component';
import { authGuard } from './auth/auth.guard';
import { FriendComponent } from './friend/friend.component';
import { NavbarComponent } from './navbar/navbar.component';

export const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  {
    path: 'app',
    canActivate: [authGuard],
    component: NavbarComponent,
    children:[
      {
        path: 'user/:id',
        component: UserComponent,
      },
      {
        path: 'friends',
        component: FriendComponent,
      },
    ],
  },
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full',
  },
  { path: '**', component: NotFoundComponent }
];
