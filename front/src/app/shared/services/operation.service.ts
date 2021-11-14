import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {OperationType} from "../../shared/model/operation-type.enum";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {AccountStatement} from "../../shared/model/account-statement";

@Injectable({
  providedIn: 'root'
})
export class OperationService {

  private baseURL = `${environment.backendUrl}`;

  constructor(private http: HttpClient) {
  }

  saveOperation = (amount: number, operationType: OperationType): Observable<any> => {
    const accountNumber = 2500; // Just for test
    const url: string = `${this.baseURL}/${accountNumber}/operations`;

    return this.http.post(url, this.buildOperationDetail(amount, operationType));
  };

  private buildOperationDetail(amount: number, operationType: OperationType) {
    return {
      amount: amount,
      operationType: operationType
    };
  }
}
