import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';

import { Task } from '../task';
import { AddTaskService } from './add-task.service';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent {

  constructor(private router: Router, private addTaskService: AddTaskService, private datePipe: DatePipe) { }
  
  task: Task = new Task();
  error: any = { isError: false, errorMessage: '' };
  isValidDate: any;

  createTask(): void {
    this.error = { isError: false, errorMessage: '' };
    this.task.startDate = this.datePipe.transform(this.task.startDate, "yyyy-MM-dd");
    this.task.endDate = this.datePipe.transform(this.task.endDate, "yyyy-MM-dd");

    this.isValidDate = this.validateDates(this.task.startDate, this.task.endDate, this.task.projectId, this.task.priority, this.task.employeeId, this.task.taskName, this.task.parentId, this.task.status, this.task.checkParent);
    if (this.isValidDate) {
      
      this.addTaskService.createTask(this.task)
        .subscribe(data => {
          alert("Task created successfully.");
        });
    };
  }
  validateDates(sDate: string, eDate: string, pProject: number, pPriority: number, eID: number, tName: string, pID: number, sStatus: string, iParent: boolean) {
    this.isValidDate = true;
    if(iParent){
      if (pProject == null || tName == null) {
        this.error = { isError: true, errorMessage: 'Project and Task fields are required.' };
        this.isValidDate = false;
      }
    }else{
      if ((sDate == null || eDate == null || pProject == null || pPriority == null || eID == null || tName == null || pID == null || sStatus == null)) {
        this.error = { isError: true, errorMessage: 'All fields are required.' };
        this.isValidDate = false;
      }
  
      if ((sDate != null && eDate != null) && (eDate) < (sDate)) {
        this.error = { isError: true, errorMessage: 'End date should be grater then start date.' };
        this.isValidDate = false;
      }

    }
    

    return this.isValidDate;
  }


}
