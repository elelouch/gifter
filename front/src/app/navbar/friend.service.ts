import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { User } from '../user/user';
import { FriendRequest } from '../friend/friend.request';

@Injectable({
  providedIn: 'root'
})
export class FriendService {
  constructor(private http: HttpClient) {}

  friendRequest(destination: number) {
    const url = `${environment.apiUrl}/follow`;
    return this.http.post<FriendRequest>(url, {destination});
  }

  removeFriendRequest(requestId: number) {
    const url = `${environment.apiUrl}/follow/${requestId}`;
    return this.http.delete(url);
  }

  checkFriendRequest(destination: number) {
    const url = `${environment.apiUrl}/follow/destination/${destination}`;
    return this.http.get<FriendRequest>(url);
  }
}
