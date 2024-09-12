import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Gift } from './gift';

@Injectable({
  providedIn: 'root'
})
export class GiftService {

  constructor(private http: HttpClient) { }

  getCurrentGifts() {
    const url = `${environment.apiUrl}/gift`;
    return this.http.get<Gift[]>(url);
  }
}
