import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { User } from './user';

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

}
