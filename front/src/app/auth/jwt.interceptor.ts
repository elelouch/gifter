import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpStatusCode } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, EMPTY, Observable, catchError, map, throwError } from "rxjs";
import { AuthService } from "./auth.service";
import { UserAuth } from "./user.auth";
import { ActivatedRoute, Router } from "@angular/router";

@Injectable()
export class JwtInterceptor implements HttpInterceptor{
  constructor(
    private auth: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ){}

  public intercept(request: HttpRequest<any>, next: HttpHandler) {
    const user = this.auth.getCurrentUser()
    if(user && user.token) {
      request = request.clone({
        setHeaders:{
          "Authorization": `Bearer ${user.token}`
        }
      })
    }
    return next.handle(request).pipe(catchError((error: HttpErrorResponse, catches) =>{
      if(error.status === HttpStatusCode.Forbidden) {
        this.auth.logout()
        this.router.navigate(["/login"]) // not authenticated
      }
      throw error;
    }));
  }
}
