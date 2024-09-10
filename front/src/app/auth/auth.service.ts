import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserAuth } from './user.auth';
import { Observable, map, BehaviorSubject } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private userBehaviorSubject: BehaviorSubject<UserAuth>
  private user$: Observable<UserAuth>

  constructor(private http: HttpClient ) {
    this.userBehaviorSubject = new BehaviorSubject<UserAuth>(
      JSON.parse(sessionStorage.getItem("currentUser") ?? '{}')
    );
    this.user$ = this.userBehaviorSubject.asObservable();
  }

  public login(email: string, password: string): Observable<void> {
    const body = {email, password}
    const url = `${environment.apiUrl}/auth/login`
    console.log("url used: ", url)
    return this
      .http
      .post<UserAuth>(url, body)
      .pipe(map(userAuth =>{
                sessionStorage.setItem("currentUserToken", JSON.stringify(userAuth))
                this.userBehaviorSubject.next(userAuth)
      }));
      // local storage solo puede tener strings hay que usar JSON.stringify aca
      // y JSON.parse en otro lado si se quiere ir pasando objetos
  }

  public getCurrentUser(){
    return this.userBehaviorSubject.value;
  }

}
