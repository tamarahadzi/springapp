import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {User} from "../../shared/models/user.model";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Role} from "../../shared/models/role.model";
import {UsersService} from "./users.service";
import {Car} from "../../shared/models/car.model";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  userForm: FormGroup;
  user: User;
  role: Role;
  users: User[];

  constructor(private fb: FormBuilder,
              private modalService: NgbModal,
              private usersService: UsersService) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers() {
    this.usersService.getAllUsers().subscribe(res => {
      console.info("res", res.body);
      this.users = res.body;
    })
  }

  showSaveUserModal(modal) {
    this.userForm = this.fb.group({
      firstName: [],
      lastName: [],
      email: [],
      password: [],
      phoneNumber: []
    });
    this.modalService.open(modal, { centered: true });
  }

  saveUser() {
    this.user = new User();
    this.user.firstName = this.userForm.get('firstName').value;
    this.user.lastName = this.userForm.get('lastName').value;
    this.user.email = this.userForm.get('email').value;
    this.user.password = this.userForm.get('password').value;
    this.user.phone = this.userForm.get('phoneNumber').value;
    this.role = new Role();
    this.role.id = 1;
    this.role.name = "ADMIN";
    this.user.role = this.role;
    console.log("user", this.user);
    this.usersService.saveUser(this.user).subscribe( res => {
      console.log("res", res);
      this.getAllUsers();
    });
  }

  deleteUser(userId: number) {
    this.usersService.deleteUser(userId).subscribe(res => {
      this.getAllUsers();
    })
  }

}
