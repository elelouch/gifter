import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserAuth } from './user.auth';
import { Observable, map, BehaviorSubject, catchError, of, EMPTY, throwError, retry } from 'rxjs';
import { environment } from '../../environments/environment';
import { USER_LOCAL_STORAGE_KEY, User } from '../user/user';
import { UserLogin } from '../user/user.login';
import { UserRegister } from '../user/user.register';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private userBehaviorSubject: BehaviorSubject<UserAuth>
  public user$: Observable<UserAuth>
  public isLoggedIn = false

  constructor(private http: HttpClient ) {
    const currentUser = sessionStorage.getItem(USER_LOCAL_STORAGE_KEY);
    this.isLoggedIn = !!currentUser
    this.userBehaviorSubject = new BehaviorSubject<UserAuth>(JSON.parse(currentUser as string));
    this.user$ = this.userBehaviorSubject.asObservable();
  }

  public login(userLogin: UserLogin) {
    const url = `${environment.apiUrl}/auth/login`
    return this
      .http
      .post<UserAuth>(url, userLogin)
      .pipe(map(userAuth =>{
                sessionStorage.setItem(USER_LOCAL_STORAGE_KEY, JSON.stringify(userAuth));
                this.userBehaviorSubject.next(userAuth);
                this.isLoggedIn = true
                return of(userAuth);
      }))
      // local storage solo puede tener strings hay que usar JSON.stringify aca
      // y JSON.parse en otro lado si se quiere ir pasando objetos
  }

  public logout() {
    this.isLoggedIn = false
    sessionStorage.removeItem(USER_LOCAL_STORAGE_KEY)
    this.userBehaviorSubject.next({} as UserAuth)
  }

  public getCurrentUser(){
    return this.userBehaviorSubject.value;
  }

  public register(userRegister: UserRegister) {
    const url = `${environment.apiUrl}/auth/register`
    return this.http.post<User>(url, userRegister)
  }
}
