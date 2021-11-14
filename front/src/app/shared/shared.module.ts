import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from "@angular/common/http";
import {SnackBarComponent} from "./component/snack-bar/snack-bar.component";

@NgModule({
  declarations: [
    SnackBarComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
  ],
  exports : [ SnackBarComponent ]
})
export class SharedModule { }
