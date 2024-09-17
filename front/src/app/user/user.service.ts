import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { User } from './user';
import { UserFollowers } from './user.followers';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}

  getCurrentUser() {
    const url = `${environment.apiUrl}/user/current`
    return this.http.get<User>(url)
  }

  getByUsername(username: string) {
    const url = `${environment.apiUrl}/user/${username}`
    return this.http.get<User>(url);
  }

  getByLikeUsername(username: string) {
    const url = `${environment.apiUrl}/user/filter`;
    return this.http.post<User[]>(url, {username})
  }

  getFollowers() {
    const url = `${environment.apiUrl}/user/followers`;
    return this.http.get<UserFollowers>(url);
  }

  getFollowing() {
    const url = `${environment.apiUrl}/user/following`;
    return this.http.get<UserFollowers>(url);
  }
}
