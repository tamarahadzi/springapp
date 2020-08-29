import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../../shared/models/user.model";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
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
  modalReference: NgbModalRef;
  user: User;
  role: Role;
  users: User[];
  userId: number;
  saveOrUpdateUser: boolean;

  constructor(private fb: FormBuilder,
              private modalService: NgbModal,
              private usersService: UsersService) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers() {
    this.usersService.getAllUsers().subscribe(res => {
      console.info("allUsers", res.body);
      this.users = res.body;
    })
  }

  showSaveUserModal(saveOrUpdate, user, modal) {
    this.saveOrUpdateUser = saveOrUpdate;
    if(!saveOrUpdate) {
      this.userForm = this.fb.group({
        firstName: [null, [Validators.required]],
        lastName: [null, [Validators.required]],
        email: [null, [Validators.required, Validators.email]],
        password: [null, [Validators.required]],
        phoneNumber: [null, [Validators.required]]
      });
    } else {
      this.userId = user.id;
      this.userForm = this.fb.group({
        firstName: [user.firstName],
        lastName: [user.lastName],
        email: [user.email],
        password: [user.password],
        phoneNumber: [user.phone]
      });
    }
    this.modalReference = this.modalService.open(modal, { centered: true });
  }

  saveUser() {
    this.user = new User();
    if(this.saveOrUpdateUser) {
      this.user.id = this.userId;
    }
    this.user.firstName = this.userForm.get('firstName').value;
    this.user.lastName = this.userForm.get('lastName').value;
    this.user.email = this.userForm.get('email').value;
    this.user.password = this.userForm.get('password').value;
    this.user.phone = this.userForm.get('phoneNumber').value;
    this.role = new Role();
    this.role.id = 1;
    this.role.name = "ADMIN";
    this.user.role = this.role;
    console.log("this.user", this.user);
    if(!this.saveOrUpdateUser) {
      this.usersService.saveUser(this.user).subscribe(res => {
        this.getAllUsers();
        this.modalReference.close();
      });
    } else {
      this.usersService.updateUser(this.user).subscribe(res => {
        this.getAllUsers();
        this.modalReference.close();
      });
    }
  }

  showDeleteUserModal(userId, modal) {
    this.userId = userId;
    this.modalReference = this.modalService.open(modal, {centered: true});
  }

  deleteUser() {
    this.usersService.deleteUser(this.userId).subscribe(res => {
      this.getAllUsers();
      this.modalReference.close();
    })
  }

}
