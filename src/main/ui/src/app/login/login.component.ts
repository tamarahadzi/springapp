import {Component, OnInit, ViewChild} from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {LoginService} from "./login.service";
import {User} from "../shared/models/user.model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  signUpForm: FormGroup;
  user: User;

  constructor(private fb: FormBuilder,
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

    });
  }

  showSignUpModal(modal) {
    this.signUpForm = this.fb.group({
      firstName: [],
      lastName: [],
      email: [],
      password: [],
      phoneNumber: []
    });
    this.modalService.open(modal, { centered: true });
  }

  signUp() {
    this.user = new User();
    this.user.firstName = this.signUpForm.get('firstName').value;
    this.user.lastName = this.signUpForm.get('lastName').value;
    this.user.email = this.signUpForm.get('email').value;
    this.user.password = this.signUpForm.get('password').value;
    this.user.phone = this.signUpForm.get('phoneNumber').value;
    this.user.roleId = 1;
    console.log("user", this.user);
    this.loginService.signUp(this.user).subscribe();
  }

}
