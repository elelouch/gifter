import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Gift } from './gift';
import { UpdateGift } from './update.gift';

@Injectable({
  providedIn: 'root'
})
export class GiftService {

  constructor(private http: HttpClient) { }

  getCurrentGifts() {
    const url = `${environment.apiUrl}/gift`;
    return this.http.get<UpdateGift>(url);
  }

  updateGifts(gifts: Gift[]) {
    const url = `${environment.apiUrl}/gift`;
    const body = { list: gifts }
    return this.http.post<UpdateGift>(url, body);
  }
}
