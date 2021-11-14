import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AccountStatement} from "../../shared/model/account-statement";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private baseURL = `${environment.backendUrl}`;

  constructor(private http: HttpClient) {
  }


  getAccountStatement = (): Observable<AccountStatement> => {
    const accountNumber = 2500; // Just for test
    const url: string = `${this.baseURL}/${accountNumber}/account-statement`;

    return this.http.get<AccountStatement>(url);
  };
}
