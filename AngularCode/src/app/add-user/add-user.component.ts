import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent {

  user: User = new User();
  users: User[];

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit() {
    this.getAllUsers();
  };

  getAllUsers() {
    this.userService.getUsers()
      .subscribe(data => {
        this.users = data;
      });
  }

  error: any = { isError: false, errorMessage: '' };
  isValidDate: any;

  addUser(): void {
    this.error = { isError: false, errorMessage: '' };
    this.isValidDate = this.validateDates(this.user.firstName, this.user.lastName, this.user.employeeId);
    if (this.isValidDate) {
      this.userService.addUser(this.user)
        .subscribe(data => {
          alert("User created successfully.");
        });

      this.getAllUsers();
    };
  }

  validateDates(fName: string, lName: string, eID: string){
    this.isValidDate = true;
    if((fName == null || lName ==null || eID == null)){
      this.error={isError:true,errorMessage:'All fields are required.'};
      this.isValidDate = false;
    }
    return this.isValidDate;
  }

  deleteUser(user: User): void {
    this.error={isError:false,errorMessage:''};
    this.userService.deleteUsers(user)
      .subscribe(data => {
        this.getAllUsers();
      })
  };

  // sorting logic
  key = 'employeeId'; // sort default by employeeId
  reverse = false;
  sortList(key) {
    this.key = key;
    this.reverse = !this.reverse;
  }


}
