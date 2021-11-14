import {OperationType} from "./operation-type.enum";

export interface Operation {

  amount: number;
  operationType: OperationType;
  date: Date;
}
