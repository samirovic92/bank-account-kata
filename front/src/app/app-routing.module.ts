import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OperationComponent} from "./features/operation/operation.component";
import {HistoryComponent} from "./features/history/history.component";

const routes: Routes = [
  { path: 'operation', component: OperationComponent },
  { path: 'history', component: HistoryComponent },
  { path: '**', redirectTo: 'operation' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
