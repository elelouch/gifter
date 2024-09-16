import { Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { UserComponent } from './user/user.component';
import { authGuard } from './auth/auth.guard';
import { FollowersComponent } from './friend/followers/followers.component';
import { FollowingComponent } from './friend/following/following.component';
import { SearchComponent } from './friend/search/search.component';
import { FriendComponent } from './friend/friend.component';

export const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  {
    path: 'user/:id',
    component: UserComponent,
    canActivate: [authGuard],
  },
  {
    path: 'friends',
    component: FriendComponent,
    children:[
      {
        path:'following',
        component: FollowingComponent
      },
      {
        path:'followers',
        component: FollowersComponent
      },
      {
        path: 'search',
        component: SearchComponent
      }
    ]
  },
  { path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: '**', component: NotFoundComponent }
];
