import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Project } from './project';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http:HttpClient) {}
  private projectUrl = '/api/project';

  public createProject(project) {
    return this.http.post<Project>(this.projectUrl,project);
  }

  public getProjects() {
    return this.http.get<Project[]>(this.projectUrl);
  }

  public updateProject(projectId,data) {
    return this.http.put<Project>(this.projectUrl +"?projectId="+ projectId, data);
  }

}
