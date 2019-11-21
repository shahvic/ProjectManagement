import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class UserService {

  constructor(private http:HttpClient) {}

  private userUrl = '/api/user';

  public getUsers() {
    return this.http.get<User[]>(this.userUrl);
  }

 public addUser(user) {
    return this.http.post<User>(this.userUrl, user);
  }

 public deleteUsers(user) {
    return this.http.delete(this.userUrl + "?employeeId="+user.employeeId);
  }
}
