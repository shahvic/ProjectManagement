import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Task } from '../Task';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class AddTaskService {

 constructor(private http:HttpClient) {}
  private taskUrl = '/api/task';

public createTask(task) {
       
    return this.http.post<Task>(this.taskUrl,task);
  }

  
}
