import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';

import { Task } from '../task';
import { ViewTaskService } from './view-task.service';

@Component({
  selector: 'app-view-task',
  templateUrl: './view-task.component.html',
  styleUrls: ['./view-task.component.css']
})
export class ViewTaskComponent implements OnInit {

  constructor(private router: Router, private viewTaskService: ViewTaskService, private datePipe: DatePipe) { }

  tasks : Task[];
  ngOnInit() {
  }

  getTasks(projectId){
    this.viewTaskService.getTasks(projectId)
    .subscribe(data =>{
      this.tasks=data;
    }
    )
  }

  // sorting logic
  key = 'projectName'; // sort default by projectName
  reverse = false;
  sortList(key) {
    this.key = key;
    this.reverse = !this.reverse;
  }

}
