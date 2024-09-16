import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Gift } from './gift';
import { UpdateGift } from './update.gift';
import { BehaviorSubject } from 'rxjs';
import { GiftDto } from './gift.dto';

@Injectable({
  providedIn: 'root',
})
export class GiftService {
  constructor(private http: HttpClient) {}

  getCurrentGifts() {
    const url = `${environment.apiUrl}/gift/list`;
    return this.http.get<UpdateGift>(url);
  }

  updateGifts(gifts: Gift[]) {
    const url = `${environment.apiUrl}/gift/list`;
    const body = { list: gifts };
    return this.http.post<UpdateGift>(url, body);
  }

  updateGift(gift: Gift) {
    const url = `${environment.apiUrl}/gift`;
    console.log('sending gift', gift);
    return this.http.post<Gift>(url, gift);
  }

  deleteGift(id: number) {
    const url = `${environment.apiUrl}/gift/${id}`;
    return this.http.delete<Gift>(url);
  }
}
