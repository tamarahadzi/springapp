import {Component, OnInit, ViewChild} from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import {NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {LoginService} from "./login.service";
import {User} from "../shared/models/user.model";
import {Router} from "@angular/router";
import {Role} from "../shared/models/role.model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  signUpForm: FormGroup;
  modalReference: NgbModalRef;
  user: User;
  role: Role;

  constructor(private fb: FormBuilder,
              private router: Router,
              private modalService: NgbModal,
              private loginService: LoginService) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: [],
      password: []
    })
  }

  login() {
    this.loginService.login(this.loginForm.get('email').value, this.loginForm.get('password').value).subscribe( res => {
      console.log("loggedUser", res.body);
      let loggedUser = new User();
      loggedUser.id = res.body.id;
      loggedUser.firstName = res.body.firstName;
      loggedUser.lastName = res.body.lastName;
      loggedUser.email = res.body.username;
      loggedUser.phone = res.body.phone;
      let role = new Role();
      if(res.body.authorities[0].authority == 'ROLE_ADMIN') {
        role.id = 1;
        role.name = "ADMIN";
      } else {
        role.id = 2;
        role.name = "USER";
      }
      loggedUser.role = role;
      localStorage.setItem("loggedUser", JSON.stringify(loggedUser));
      this.router.navigate(['/navbar']);
    });
    //this.router.navigate(['/navbar']);
  }

  showSignUpModal(modal) {
    this.signUpForm = this.fb.group({
      firstName: [],
      lastName: [],
      email: [],
      password: [],
      phoneNumber: []
    });
    this.modalReference = this.modalService.open(modal, { centered: true });
  }

  signUp() {
    this.user = new User();
    this.user.firstName = this.signUpForm.get('firstName').value;
    this.user.lastName = this.signUpForm.get('lastName').value;
    this.user.email = this.signUpForm.get('email').value;
    this.user.password = this.signUpForm.get('password').value;
    this.user.phone = this.signUpForm.get('phoneNumber').value;
    this.role = new Role();
    this.role.id = 2;
    this.role.name = "USER";
    this.user.role = this.role;
    console.log("user", this.user);
    this.loginService.signUp(this.user).subscribe(res => {
      this.modalReference.close();
    });
  }

}
