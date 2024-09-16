import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { User } from '../user/user';

@Injectable({
  providedIn: 'root'
})
export class FriendService {
  constructor(private http: HttpClient) {}

  getByLikeUsername(username: string) {
    const url = `${environment.apiUrl}/user/filter`;
    return this.http.post<User[]>(url, {username})
  }
}
