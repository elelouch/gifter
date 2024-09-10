import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";
import { AuthService } from "./auth.service";
import { UserAuth } from "./user.auth";

@Injectable()
export class JwtInterceptor implements HttpInterceptor{
  constructor(private auth: AuthService){}

  public intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const user = this.auth.getCurrentUser()
    if(user && user.token) {
      request = request.clone({
        setHeaders:{
          "Authorization": `Bearer ${user.token}`
        }
      })
    }
    return next.handle(request)
  }
}
