import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Task } from '../Task';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ViewTaskService {

  constructor(private http:HttpClient) { }
  private taskUrl = '/api/task';

  public getTasks(projectId) {
    return this.http.get<Task[]>(this.taskUrl+"/?projectId="+projectId);
  }

  public updateTask(task,data) {
    alert("Task: "+task)
    return this.http.put(this.taskUrl + task.taskId, data);
  }
}
