import {Operation} from "./operation";

export interface AccountStatement {

  clientId: number;
  accountNumber: number;
  balance: number;
  operations: Operation[];
}
