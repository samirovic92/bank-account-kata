import {Component, OnInit} from '@angular/core';
import {AccountStatement} from "../../shared/model/account-statement";
import {Operation} from "../../shared/model/operation";
import {MatTableDataSource} from "@angular/material/table";
import {AccountService} from "../../shared/services/account.service";

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.scss']
})
export class HistoryComponent implements OnInit {

  displayedColumns: string[] = ['amount', 'operationType', 'date' ];
  dataSource = new MatTableDataSource<Operation>([]);
  accountStatement: AccountStatement;

  constructor(private accountService: AccountService) {
  }

  ngOnInit(): void {
    this.getAccountStatement();
  }

  private getAccountStatement = () => {

    this.accountService.getAccountStatement()
      .subscribe(accountStatement => {
        this.accountStatement = accountStatement;
        this.dataSource.data = accountStatement.operations;
      })
  };
}
