import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
import { User } from "../user/user";

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  constructor(private http: HttpClient) {}
  getAllUsers() {
    const url = `${environment.apiUrl}/admin/user`
    return this.http.get<User[]>(url)
  }
}
