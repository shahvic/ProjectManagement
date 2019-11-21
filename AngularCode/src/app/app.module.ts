import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from "@angular/common/http";
import { AppRoutingModule } from './app.routing.module';
import { OrderModule } from 'ngx-order-pipe';
import {DatePipe} from '@angular/common';

import { AppComponent } from './app.component';
import { AddUserComponent } from './add-user/add-user.component';
import { AddProjectComponent } from './add-project/add-project.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { ViewTaskComponent } from './view-task/view-task.component';

import {UserService} from './user.service';
import {ProjectService} from './add-project/project.service';




@NgModule({
  declarations: [
    AppComponent,
    AddUserComponent,
    AddProjectComponent,
    AddTaskComponent,
    ViewTaskComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    OrderModule,
    ReactiveFormsModule
  ],
  providers: [UserService,ProjectService,DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
