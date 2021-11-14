import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {OperationType} from "../../shared/model/operation-type.enum";
import {OperationService} from "../../shared/services/operation.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SnackBarComponent} from "../../shared/component/snack-bar/snack-bar.component";

@Component({
  selector: 'app-operation',
  templateUrl: './operation.component.html',
  styleUrls: ['./operation.component.scss']
})
export class OperationComponent implements OnInit {

  amountFormControl: FormControl;
  selectedOperation;
  operationTypes;

  constructor(private operationService: OperationService,
              private _snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.initData();
    this.initDepositFormControl();
  }

  private initData() {
    this.selectedOperation = OperationType.DEPOSIT;
    this.operationTypes = [
      {value: OperationType.DEPOSIT, label: 'Deposit'},
      {value: OperationType.WITHDRAWAL, label: 'withdrawal'}
    ];
  }

  private initDepositFormControl() {
    this.amountFormControl = new FormControl('', [Validators.required, Validators.pattern("[0-9]*")]);
  }

  saveOperation() {
    const amount: number = this.amountFormControl.value;
    this.operationService.saveOperation(amount, this.selectedOperation)
      .subscribe(() => { },
        error => this._snackBar.openFromComponent(SnackBarComponent, {data: error.error.message, duration: 2000})
      )
  }

}
